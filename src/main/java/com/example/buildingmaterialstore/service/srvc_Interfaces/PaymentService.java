package com.example.buildingmaterialstore.service.srvc_Interfaces;

import com.example.buildingmaterialstore.model.dto.request.PaymentRequestDto;
import com.example.buildingmaterialstore.model.dto.response.PaymentResponseDto;

public interface PaymentService {
    PaymentResponseDto processPayment(PaymentRequestDto paymentRequestDto);
}
