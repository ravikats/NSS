package com.empay.irfservice.controller;

import com.empay.common.irf.HttpIrfCalculator;
import com.empay.common.irf.IrfCalculateRequest;
import com.empay.common.irf.IrfCalculateResponse;
import com.empay.common.irf.IrfTxnData;
import com.empay.common.vo.IRFResultVo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.mock.env.MockEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
                properties = { "irf.service.sec=test-secret" })
class IrfCalculationControllerIT {

    @LocalServerPort
    int port;

    @Autowired
    private TestRestTemplate restTemplate;   // server-side test client

    private String base() {
        return "http://localhost:" + port + "/irf/v1";
    }

    private IrfTxnData sampleTxn() {
        return new IrfTxnData()
                .setInsCode(7085)
                .setNetwork("VISA")
                .setMcc("5499")
                .setTxnAmount(100.0)
                .setSetlAmount(100.0)
                .setTxnCurCode("784")
                .setSetlCurCode("784")
                .setPosEntryMode("012")
                .setApprovalCode("ABC123")
                .setResponseCode("00")
                .setMotoEcomIndicator(null)
                .setFeePgmIndicator("N")
                .setReImbursementAttribute('B')
                .setTrlCapabilities(null)
                .setTerminalCapability("00")
                .setMvv("0A01020304")
                .setEncCardNumber("411111111")
                .setTxnDateTime(LocalDateTime.now())
                .setAcqInstConCode("784")
                .setRrn("123456789012");
    }

    @Test
    void calculateViaRest_returnsCalculatedTrueWithFallbackVo() {
        IrfCalculateRequest req = new IrfCalculateRequest()
                .setNetwork("VISA")
                .setInsCode(7085)
                .setCardNumber("411111111")
                .setTxnData(sampleTxn());

        var resp = restTemplate.postForEntity(
                base() + "/calculate?sec=test-secret", req, IrfCalculateResponse.class);

        assertThat(resp.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(resp.getBody()).isNotNull();
        assertThat(resp.getBody().isCalculated()).isTrue();
        IRFResultVo vo = resp.getBody().getResult();
        assertThat(vo).isNotNull();
        assertThat(vo.getIrfCountry()).isEqualTo("XX");   // no BIN range -> fallback
        assertThat(vo.getIrfAmount()).isEqualTo(0.0);
    }

    @Test
    void rejectsBadSecret_returns401() {
        IrfCalculateRequest req = new IrfCalculateRequest()
                .setNetwork("VISA").setInsCode(7085).setCardNumber("411111111")
                .setTxnData(sampleTxn());

        var resp = restTemplate.postForEntity(
                base() + "/calculate?sec=wrong", req, IrfCalculateResponse.class);

        assertThat(resp.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);
    }

    @Test
    void clientRoundTrip_httpIrfCalculatorCallsLiveServer() {
        Environment env = new MockEnvironment()
                .withProperty("irf.service.url", "http://localhost:" + port)
                .withProperty("irf.service.sec", "test-secret");

        IrfTxnData data = sampleTxn();
        IRFResultVo vo = new HttpIrfCalculator(new RestTemplate(), env)
                .calculate(7085, data, "411111111");

        assertThat(vo).isNotNull();
        assertThat(vo.getIrfCountry()).isEqualTo("XX");
    }
}
