package com.example.buildingmaterialstore.model.dto.response;


import lombok.Data;

import java.util.List;

@Data
public class OrderResponseDto {

    private Long id;
    private UserResponseDto user;
    private List<OrderItemResponseDto> orderItems;
    private Double totalAmount;
    private AddressResponseDto deliveryAddress;
    private String status;
}
