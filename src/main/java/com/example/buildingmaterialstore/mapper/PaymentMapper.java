package com.example.buildingmaterialstore.mapper;

import com.example.buildingmaterialstore.entity.Payment;
import com.example.buildingmaterialstore.model.dto.request.PaymentRequestDto;
import com.example.buildingmaterialstore.model.dto.response.PaymentResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class PaymentMapper {

    public abstract PaymentResponseDto toResponse(Payment payment);
    public abstract Payment toEntity(PaymentRequestDto paymentRequestDto);
}
