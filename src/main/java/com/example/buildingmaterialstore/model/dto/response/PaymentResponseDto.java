package com.example.buildingmaterialstore.model.dto.response;

import com.example.buildingmaterialstore.model.enums.PaymentStatus;
import com.example.buildingmaterialstore.entity.Payment.PaymentMethod;
import lombok.Data;

@Data
public class PaymentResponseDto {
    private Long id;
    private Long orderId;
    private PaymentStatus status;
    private PaymentMethod method;
}
