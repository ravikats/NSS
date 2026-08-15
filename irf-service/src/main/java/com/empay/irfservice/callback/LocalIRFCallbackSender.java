package com.empay.irfservice.callback;

import com.empay.common.irf.IRFCallbackSender;
import com.empay.common.irf.IrfResultData;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Server-side implementation of {@link IRFCallbackSender}.
 *
 * <ul>
 *   <li>{@code enqueue} persists a PENDING row (matches TLF behaviour, plus
 *       the insCode used by MPGS's bulk flush).</li>
 *   <li>{@code flush} sends pending rows for a job to the scheme callback URL
 *       and records the response. Done asynchronously so the caller (and the
 *       original listener thread) is not blocked (#7/#8).</li>
 *   <li>{@code retry} re-sends a single callback by its stored serial number.</li>
 * </ul>
 *
 * TODO: populate the downstream scheme callback URL per institution (currently
 * read from {@code irf.callback.target.url}).
 */
@Service
public class LocalIRFCallbackSender implements IRFCallbackSender {

    private static final char PENDING = 'P';
    private static final char SENT = 'S';
    private static final char ERROR = 'E';

    private final IRFCallbackRepository repository;
    private final RestTemplate restTemplate;
    private final Environment env;

    public LocalIRFCallbackSender(IRFCallbackRepository repository,
                                  RestTemplate restTemplate,
                                  Environment env) {
        this.repository = repository;
        this.restTemplate = restTemplate;
        this.env = env;
    }

    @Override
    @Transactional
    public Integer enqueue(IrfResultData data) {
        IRFCallbackEntity entity = new IRFCallbackEntity();
        entity.setLastUpdated(LocalDateTime.now());
        entity.setInstitutionCode(data.getInsCode());
        entity.setRefSerNumber(data.getRefSerNumber());
        entity.setStatus(PENDING);
        entity.setRequest(buildRequestJson(data));
        entity.setCpMid(data.getCpMid());
        entity.setUniqueId(data.getUniqueId());
        entity.setIrdCode(data.getIrdCode());
        entity.setFixed(data.getFixed());
        entity.setPercentage(data.getPercentage());
        entity.setIrfAmount(data.getIrfAmount());
        entity.setTxnAmount(data.getTxnAmount());
        entity.setRrn(data.getRrn());
        entity.setMti(data.getMti());
        entity.setDomIntlFlag(data.getDomIntlFlag());
        entity.setCredit(data.getCredit());
        entity.setCardClassification(data.getCardClassification());
        IRFCallbackEntity saved = repository.save(entity);
        return saved.getSerialNumber();
    }

    private String buildRequestJson(IrfResultData data) {
        // Minimal JSON payload for the downstream callback. Replaced later with
        // a proper object mapper if the scheme contract tightens.
        return String.format(
                "{\"cpMid\":\"%s\",\"uniqueId\":\"%s\",\"irdCode\":\"%s\",\"fixed\":%s,"
                + "\"percentage\":%s,\"irfAmount\":%s,\"txnAmount\":%s,\"rrn\":\"%s\","
                + "\"mti\":\"%s\",\"domIntlFlag\":\"%s\",\"isCredit\":%s,"
                + "\"cardClassification\":\"%s\"}",
                nullSafe(data.getCpMid()), nullSafe(data.getUniqueId()), nullSafe(data.getIrdCode()),
                data.getFixed(), data.getPercentage(), data.getIrfAmount(), data.getTxnAmount(),
                nullSafe(data.getRrn()), nullSafe(data.getMti()),
                (data.getDomIntlFlag() == null) ? "" : data.getDomIntlFlag(),
                (data.getCredit() == null) ? "" : data.getCredit().toString(),
                nullSafe(data.getCardClassification()));
    }

    private static String nullSafe(String s) {
        return (s == null) ? "" : s.replace("\"", "\\\"");
    }

    @Override
    @Transactional
    public void flush(Integer insCode, Integer jobNumber) {
        List<IRFCallbackEntity> pending = (jobNumber == null)
                ? repository.findByStatusOrderBySerialNumber(PENDING)
                : repository.findByInstitutionCodeAndJobNumberAndStatusOrderBySerialNumber(insCode, jobNumber, PENDING);
        String targetUrl = env.getProperty("irf.callback.target.url");
        for (IRFCallbackEntity row : pending) {
            try {
                String resp = restTemplate.postForObject(targetUrl, row.getRequest(), String.class);
                row.setStatus(SENT);
                row.setResponse(resp);
            } catch (Exception e) {
                row.setStatus(ERROR);
                row.setResponse(e.getMessage());
            }
            row.setLastUpdated(LocalDateTime.now());
            repository.save(row);
        }
    }

    @Override
    @Transactional
    public boolean retry(Integer refSerNumber) {
        return repository.findById(refSerNumber).map(row -> {
            try {
                String resp = restTemplate.postForObject(
                        env.getProperty("irf.callback.target.url"), row.getRequest(), String.class);
                row.setStatus(SENT);
                row.setResponse(resp);
                row.setLastUpdated(LocalDateTime.now());
                repository.save(row);
                return true;
            } catch (Exception e) {
                row.setStatus(ERROR);
                row.setResponse(e.getMessage());
                row.setLastUpdated(LocalDateTime.now());
                repository.save(row);
                return false;
            }
        }).orElse(false);
    }
}
