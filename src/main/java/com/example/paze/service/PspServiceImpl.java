package com.example.paze.service;

import com.example.paze.client.PspClient;
import org.springframework.stereotype.Service;

@Service
public class PspServiceImpl implements PspService {

    private final PspClient client;

    public PspServiceImpl(PspClient client) {
        this.client = client;
    }

    @Override
    public String getRedirectUrl(int amount) {
        return client.createPayment(amount).result().redirectUrl();
    }

}
