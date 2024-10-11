package com.example.buildingmaterialstore.service.impl;

import com.example.buildingmaterialstore.model.dto.request.PaymentRequestDto;
import com.example.buildingmaterialstore.model.dto.response.PaymentResponseDto;
import com.example.buildingmaterialstore.repository.PaymentRepository;
import com.example.buildingmaterialstore.service.srvc_Interfaces.PaymentService;
import lombok.Data;
import org.springframework.stereotype.Service;

@Service
@Data
public class PaymentServiceImpl implements PaymentService {

    private PaymentRepository paymentRepository;

    @Override
    public PaymentResponseDto processPayment(PaymentRequestDto paymentRequestDto) {
        return null;
    }
}
