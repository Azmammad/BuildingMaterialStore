package com.example.buildingmaterialstore.model.dto.request;

import com.example.buildingmaterialstore.entity.Payment.PaymentMethod;
import lombok.Data;

@Data
public class PaymentRequestDto {
    private Long orderId;
    private PaymentMethod method;
}
