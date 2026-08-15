package outsvc

import (
	"encoding/json"
	"net/http"
)

// OutgoingController is the Go port of the Java OutGoingController
// (/outgoing/ endpoints).
type OutgoingController struct {
	svc *OutgoingService
}

// NewOutgoingController wires the HTTP handlers.
func NewOutgoingController(svc *OutgoingService) *OutgoingController {
	return &OutgoingController{svc: svc}
}

// GenerateOutgoing handles POST /outgoing/v1/generateOutgoing.
func (c *OutgoingController) GenerateOutgoing(w http.ResponseWriter, r *http.Request) {
	var vo OutGoingRequestVo
	if err := json.NewDecoder(r.Body).Decode(&vo); err != nil {
		writeMessage(w, http.StatusBadRequest, "Invalid request")
		return
	}
	if !outgoingNetworks[vo.Network] {
		writeMessage(w, http.StatusBadRequest, "network must match ^(MASTERCARD|VISA|RUPAY|AMEX|JAYWAN)$")
		return
	}
	formatCode := 0
	switch vo.Network {
	case "MASTERCARD":
		formatCode = c.svc.cfg.MastercardSysCode
	case "VISA":
		formatCode = c.svc.cfg.VisaSysCode
	case "JAYWAN":
		formatCode = c.svc.cfg.JaywanSysCode
	case "AMEX":
		formatCode = c.svc.cfg.AmexSysCode
	case "MERCURY":
		formatCode = c.svc.cfg.MercurySysCode
	}
	msg := c.svc.ProcessAndMoveData(r.Context(), &vo, c.svc.cfg.InsCode, c.svc.cfg.UpdatedUser, formatCode, c.svc.cfg.InsShortName)
	writeMessage(w, http.StatusOK, msg)
}

// RevertLastOutgoing handles POST /outgoing/v1/revertLastOutgoing.
func (c *OutgoingController) RevertLastOutgoing(w http.ResponseWriter, r *http.Request) {
	var vo OutGoingRequestVo
	if err := json.NewDecoder(r.Body).Decode(&vo); err != nil {
		writeMessage(w, http.StatusBadRequest, "Invalid request")
		return
	}
	msg := c.svc.RevertLastOutgoingData(r.Context(), vo.Network, c.svc.cfg.InsCode)
	writeMessage(w, http.StatusOK, msg)
}

// UpdateRejectedData handles PUT /outgoing/v1/updateRejectedData.
func (c *OutgoingController) UpdateRejectedData(w http.ResponseWriter, r *http.Request) {
	writeMessage(w, http.StatusOK, "Not yet implemented")
}

// GenerateCollectionOnly handles POST /outgoing/v1/generateCollectionOnly.
func (c *OutgoingController) GenerateCollectionOnly(w http.ResponseWriter, r *http.Request) {
	writeMessage(w, http.StatusOK, "Not yet implemented")
}

// RevertLastCollectionOnly handles POST /outgoing/v1/revertLastCollectionOnly.
func (c *OutgoingController) RevertLastCollectionOnly(w http.ResponseWriter, r *http.Request) {
	writeMessage(w, http.StatusOK, "Not yet implemented")
}

func writeMessage(w http.ResponseWriter, status int, msg string) {
	w.Header().Set("Content-Type", "application/json")
	w.WriteHeader(status)
	_ = json.NewEncoder(w).Encode(map[string]string{"message": msg})
}
