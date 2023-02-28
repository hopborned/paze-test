package com.example.paze.client;

import com.example.paze.dto.PspResponse;
import com.example.paze.properties.PazeProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class PspRestTemplateClient implements PspClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(PspRestTemplateClient.class);

    private final RestTemplate restTemplate;
    private final String URL;

    public PspRestTemplateClient(RestTemplate restTemplate,
                                 PazeProperties pazeProperties) {
        this.restTemplate = restTemplate;
        URL = pazeProperties.url();
    }

    @Override
    public PspResponse createPayment(int amount) {
        HttpEntity<Map<String, Object>> requestEntity = createRequest(amount);
        PspResponse pspResponse;
        try {
            pspResponse = restTemplate.postForObject(URL, requestEntity, PspResponse.class);
        } catch (RestClientException e) {
            LOGGER.error("Error occurred while sending request ", e);
            throw e;
        }

        return pspResponse;
    }

    private static HttpEntity<Map<String, Object>> createRequest(int amount) {
        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("paymentType", "DEPOSIT");
        requestMap.put("amount", amount);
        requestMap.put("currency", "EUR");
        return new HttpEntity<>(requestMap);
    }
}
