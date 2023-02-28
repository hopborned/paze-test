package com.example.paze.client;

import com.example.paze.dto.PspResponse;
import com.example.paze.dto.PspResult;
import com.example.paze.properties.PazeProperties;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.client.response.MockRestResponseCreators;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;

@SpringBootTest
class PspRestTemplateClientTest {

    private MockRestServiceServer server;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private PspRestTemplateClient client;
    @Autowired
    private PazeProperties properties;

    @BeforeEach

    public void setUp() {
        MockRestServiceServer.createServer(restTemplate)
                .expect(requestTo(properties.url()))
                .andExpect(method(HttpMethod.POST))
                .andRespond(MockRestResponseCreators.withSuccess(
                        """
                                {
                                  "timestamp": "2023-02-28T17:41:39.011+00:00",
                                  "status": 200,
                                  "result": {
                                    "id": "559cec88261a444ca205302016da7b39",
                                    "paymentType": "DEPOSIT",
                                    "state": "CHECKOUT",
                                    "amount": 100,
                                    "currency": "EUR",
                                    "redirectUrl": "https://demo.paze.eu/payment/559cec88261a444ca205302016da7b39"
                                  }
                                }
                                """, MediaType.APPLICATION_JSON));

    }

    @Test
    void whenSuccessResponseThenSuccess() {
        PspResponse payment = client.createPayment(100);

        assertThat(payment).isEqualTo(
                new PspResponse(
                        LocalDateTime.parse("2023-02-28T17:41:39.011+00:00", DateTimeFormatter.ISO_DATE_TIME),
                        200,
                        new PspResult(
                                "559cec88261a444ca205302016da7b39",
                                "DEPOSIT",
                                "CHECKOUT",
                                100,
                                "EUR",
                                "https://demo.paze.eu/payment/559cec88261a444ca205302016da7b39"
                        )
                )
        );
    }
}