package com.example.buildingmaterialstore.service.srvc_Interfaces;

import com.example.buildingmaterialstore.model.dto.request.PaymentRequestDto;
import com.example.buildingmaterialstore.model.dto.response.PaymentResponseDto;
import com.example.buildingmaterialstore.model.enums.PaymentStatus;

public interface PaymentService {
    PaymentResponseDto createPayment(PaymentRequestDto paymentRequestDto);
    PaymentResponseDto getPaymentById(Long id);
    void updatePaymentStatus(Long id, PaymentStatus status);
}
