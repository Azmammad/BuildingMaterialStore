package com.example.buildingmaterialstore.model.response;

import lombok.Data;

@Data
public class OrderItemResponseDto {

    private Long id;
    private ProductResponseDto product;
    private Integer quantity;
}