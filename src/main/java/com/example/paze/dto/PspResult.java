package com.example.paze.dto;

public record PspResult(
        String id,
        String paymentType,
        String state,
        int amount,
        String currency,
        String redirectUrl
) {

}
