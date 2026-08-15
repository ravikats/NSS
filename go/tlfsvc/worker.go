package tlfsvc

import (
	"context"
	"sync"
	"time"
)

// WorkerPool runs the async IRF/finalize half of the online TLF flow (§7.12 of
// IRF_SERVICE_HANDOVER.md). Stage 1 (Consumer.processRequest) validates,
// INSERTs the POS_TRANSACTIONS row at PTR_GEN_STATUS=9 (in-flight) and Submit's
// a workItem; a bounded set of workers pops items, runs stage 2
// (irf-service calculate + IRF update + outgoing status + callback) and flips
// the row to PTR_GEN_STATUS=3, at which point the settlement split may read it.
//
// Submit blocks while the queue is full, so the Kafka consumer stops polling
// and stops committing offsets — that natural backpressure is the designed
// protection when irf-service cannot keep up. Because the decrypted PAN needed
// for the irf-service call is only held in memory, the reaper can re-enqueue
// only items still present here; after a restart, rows left at 9 are logged as
// orphans for ops review.
type WorkerPool struct {
	svc     *Service
	workers int
	queue   chan *workItem
	mu      sync.Mutex
	bySer   map[int]*workItem
}

// NewWorkerPool builds a pool with `workers` goroutines and a bounded queue of
// `queueSize` in-flight items.
func NewWorkerPool(svc *Service, workers, queueSize int) *WorkerPool {
	return &WorkerPool{
		svc:     svc,
		workers: workers,
		queue:   make(chan *workItem, queueSize),
		bySer:   make(map[int]*workItem),
	}
}

// Submit enqueues a work item, blocking while the queue is full so the caller
// (the Kafka consumer) stops committing offsets — natural backpressure. The
// item is registered in bySer before the blocking send so the reaper can find
// it.
func (p *WorkerPool) Submit(ctx context.Context, w *workItem) {
	p.mu.Lock()
	p.bySer[w.ser] = w
	p.mu.Unlock()
	select {
	case p.queue <- w:
	case <-ctx.Done():
	}
}

// Start launches the worker goroutines. The pool runs until ctx is cancelled.
func (p *WorkerPool) Start(ctx context.Context) {
	for i := 0; i < p.workers; i++ {
		go p.run(ctx)
	}
}

func (p *WorkerPool) run(ctx context.Context) {
	for {
		select {
		case <-ctx.Done():
			return
		case w := <-p.queue:
			if w == nil {
				return
			}
			if err := p.svc.Stage2(ctx, w); err != nil {
				// Leave the row at PTR_GEN_STATUS=9; the reaper re-enqueues it.
				p.svc.Log.Warn("stage2 failed", "ser", w.ser, "err", err)
				continue
			}
			p.mu.Lock()
			delete(p.bySer, w.ser)
			p.mu.Unlock()
		}
	}
}

// Reap periodically re-enqueues rows stuck at PTR_GEN_STATUS=9 for longer than
// age (a worker failure or a lost item). Only items still registered in bySer
// (i.e. the process is alive and holds the decrypted PAN) can be replayed;
// anything else is an orphan from a previous run and is logged for ops review.
func (p *WorkerPool) Reap(ctx context.Context, age, every time.Duration) {
	ticker := time.NewTicker(every)
	defer ticker.Stop()
	for {
		select {
		case <-ctx.Done():
			return
		case <-ticker.C:
			p.reapOnce(ctx, time.Now().Add(-age))
		}
	}
}

// reapOnce is the reaper body, separated for tests.
func (p *WorkerPool) reapOnce(ctx context.Context, olderThan time.Time) error {
	serials, err := p.svc.Store.FindPendingIRFRows(ctx, olderThan)
	if err != nil {
		p.svc.Log.Warn("reap: find pending rows failed", "err", err)
		return err
	}
	for _, ser := range serials {
		p.mu.Lock()
		w, ok := p.bySer[ser]
		p.mu.Unlock()
		if !ok {
			p.svc.Log.Warn("reap: orphaned in-flight row; stage 2 cannot be replayed without the decrypted PAN",
				"ser", ser)
			continue
		}
		select {
		case p.queue <- w:
		case <-ctx.Done():
			return ctx.Err()
		}
	}
	return nil
}
