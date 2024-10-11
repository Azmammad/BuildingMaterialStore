package com.example.buildingmaterialstore.model.request;

import lombok.Data;

@Data
public class PaymentRequestDto {
    private Long orderId;
    private String method;
}