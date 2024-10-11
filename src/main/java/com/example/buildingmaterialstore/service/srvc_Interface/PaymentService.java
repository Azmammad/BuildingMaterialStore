package com.example.buildingmaterialstore.service.srvc_Interface;

import com.example.buildingmaterialstore.model.request.PaymentRequestDto;
import com.example.buildingmaterialstore.model.response.PaymentResponseDto;

public interface PaymentService {
    PaymentResponseDto processPayment(PaymentRequestDto paymentRequestDto);
}
