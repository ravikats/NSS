package com.empay.common.irf;

import com.empay.common.vo.IRFResultVo;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.web.client.RestTemplate;
import org.springframework.core.env.Environment;

/**
 * REST client implementation of {@link IrfCalculator}.
 *
 * TLF and MPGS each inject this bean (with their own endpoint/sec-param
 * config) and call {@link #calculate} exactly like the in-process calculator,
 * so the orchestration code does not change - only the wiring switches the
 * Spring profile/bean.
 */
public class HttpIrfCalculator implements IrfCalculator {

    private final RestTemplate restTemplate;
    private final Environment env;
    private final MediaType contentType;

    public HttpIrfCalculator(RestTemplate restTemplate, Environment env) {
        this(restTemplate, env, MediaType.APPLICATION_JSON);
    }

    public HttpIrfCalculator(RestTemplate restTemplate, Environment env, MediaType contentType) {
        this.restTemplate = restTemplate;
        this.env = env;
        this.contentType = contentType;
    }

    @Override
    public IRFResultVo calculate(Integer insCode, IrfTxnData txnData, String cardNumber) {
        String baseUrl = env.getRequiredProperty("irf.service.url");
        String url = UriComponentsBuilder.fromHttpUrl(baseUrl + "/irf/v1/calculate")
                .queryParam("sec", env.getRequiredProperty("irf.service.sec"))
                .toUriString();
        String network = (txnData == null) ? null : txnData.getNetwork();
        IrfCalculateRequest body = new IrfCalculateRequest(network, insCode, txnData, cardNumber);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(contentType);
        HttpEntity<IrfCalculateRequest> entity = new HttpEntity<>(body, headers);

        ResponseEntity<IrfCalculateResponse> response =
                restTemplate.postForEntity(url, entity, IrfCalculateResponse.class);

        if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
            return response.getBody().getResult();
        }
        return null;
    }
}
