package com.empay.common.irf;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.web.client.RestTemplate;
import org.springframework.core.env.Environment;

/**
 * REST client implementation of {@link IRFCallbackSender}.
 *
 * Persists the callback on the server (which owns the IRFCallbackEntity) and
 * lets the server decide the synchronous/deferred strategy.
 */
public class HttpIRFCallbackSender implements IRFCallbackSender {

    private final RestTemplate restTemplate;
    private final Environment env;

    public HttpIRFCallbackSender(RestTemplate restTemplate, Environment env) {
        this.restTemplate = restTemplate;
        this.env = env;
    }

    @Override
    public Integer enqueue(IrfResultData data) {
        String baseUrl = env.getRequiredProperty("irf.service.url");
        String url = UriComponentsBuilder.fromHttpUrl(baseUrl + "/irf/v1/callback")
                .queryParam("sec", env.getRequiredProperty("irf.service.sec"))
                .toUriString();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<IrfResultData> entity = new HttpEntity<>(data, headers);
        ResponseEntity<Integer> response = restTemplate.postForEntity(url, entity, Integer.class);
        if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
            return response.getBody();
        }
        return null;
    }

    @Override
    public void flush(Integer insCode, Integer jobNumber) {
        String baseUrl = env.getRequiredProperty("irf.service.url");
        String url = UriComponentsBuilder.fromHttpUrl(baseUrl + "/irf/v1/callback/flush")
                .queryParam("sec", env.getRequiredProperty("irf.service.sec"))
                .queryParam("insCode", insCode)
                .queryParam("jobNumber", jobNumber)
                .toUriString();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        restTemplate.postForObject(url, new HttpEntity<>(headers), String.class);
    }

    @Override
    public boolean retry(Integer refSerNumber) {
        String baseUrl = env.getRequiredProperty("irf.service.url");
        String url = UriComponentsBuilder.fromHttpUrl(baseUrl + "/irf/v1/callback/retry")
                .queryParam("sec", env.getRequiredProperty("irf.service.sec"))
                .queryParam("refSerNumber", refSerNumber)
                .toUriString();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        ResponseEntity<Boolean> response =
                restTemplate.postForEntity(url, new HttpEntity<>(headers), Boolean.class);
        return response.getStatusCode().is2xxSuccessful()
                && Boolean.TRUE.equals(response.getBody());
    }
}
