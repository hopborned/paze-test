package com.example.paze.client;

import com.example.paze.dto.PspResponse;

public interface PspClient {
    PspResponse createPayment(int amount);
}
