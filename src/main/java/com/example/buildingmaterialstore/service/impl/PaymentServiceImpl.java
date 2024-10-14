package com.example.buildingmaterialstore.service.impl;

import com.example.buildingmaterialstore.entity.Order;
import com.example.buildingmaterialstore.entity.Payment;
import com.example.buildingmaterialstore.mapper.PaymentMapper;
import com.example.buildingmaterialstore.model.dto.request.PaymentRequestDto;
import com.example.buildingmaterialstore.model.dto.response.PaymentResponseDto;
import com.example.buildingmaterialstore.model.enums.PaymentStatus;
import com.example.buildingmaterialstore.model.exception.handle.ResourceNotFoundException;
import com.example.buildingmaterialstore.repository.OrderRepository;
import com.example.buildingmaterialstore.repository.PaymentRepository;
import com.example.buildingmaterialstore.service.srvc_Interfaces.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final OrderRepository orderRepository;
    private final PaymentMapper paymentMapper;

    @Override
    public PaymentResponseDto createPayment(PaymentRequestDto paymentRequestDto) {
        log.info("-> Creating payment for order id {}", paymentRequestDto.getOrderId());

        Order order = orderRepository.findById(paymentRequestDto.getOrderId())
                .orElseThrow(() -> {
                    log.error("-> Order with id {} not found", paymentRequestDto.getOrderId());
                    return new ResourceNotFoundException("Order not found");
                });

        Payment payment = new Payment();
        payment.setOrder(order);
        payment.setMethod(paymentRequestDto.getMethod());
        payment.setStatus(PaymentStatus.PENDING);

        payment = paymentRepository.save(payment);
        log.info("-> Payment created successfully with id {}", payment.getId());
        return paymentMapper.toResponse(payment);
    }

    @Override
    public PaymentResponseDto getPaymentById(Long id) {
        log.info("-> Fetching payment with id {}", id);

        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("-> Payment with id {} not found", id);
                    return new ResourceNotFoundException("Payment not found");
                });

        return paymentMapper.toResponse(payment);
    }

    @Override
    public void updatePaymentStatus(Long id, PaymentStatus status) {
        log.info("-> Updating payment status for payment id {} to {}", id, status);

        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("-> Payment with id {} not found", id);
                    return new ResourceNotFoundException("Payment not found");
                });
        payment.setStatus(status);
        paymentRepository.save(payment);
        log.info("-> Payment status updated successfully for payment id {}", id);
    }
}
