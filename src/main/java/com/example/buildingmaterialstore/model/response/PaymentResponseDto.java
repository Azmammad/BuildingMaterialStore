package com.example.buildingmaterialstore.model.response;

import lombok.Data;

@Data
public class PaymentResponseDto {

    private Long id;
    private String status;
    private String method;
    private String transactionId;
}