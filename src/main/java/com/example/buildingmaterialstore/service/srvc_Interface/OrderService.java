package com.example.buildingmaterialstore.service.srvc_Interface;

import com.example.buildingmaterialstore.model.request.OrderRequestDto;
import com.example.buildingmaterialstore.model.response.OrderResponseDto;

import java.util.List;

public interface OrderService {

    OrderResponseDto placeOrder(OrderRequestDto orderRequestDto);
    OrderResponseDto getOrderById(Long id);
    List<OrderResponseDto> getOrdersByUserId(Long userId);
    void cancelOrder(Long id);
}
