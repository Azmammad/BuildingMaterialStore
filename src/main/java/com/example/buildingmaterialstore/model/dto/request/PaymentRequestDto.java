package com.example.buildingmaterialstore.model.dto.request;

import lombok.Data;

@Data
public class PaymentRequestDto {
    private Long orderId;
    private String method;
}