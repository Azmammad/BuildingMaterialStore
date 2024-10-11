package com.example.buildingmaterialstore.model.dto.response;

import lombok.Data;

@Data
public class CartItemResponseDto {

    private Long id;
    private ProductResponseDto product;
    private Integer quantity;
}
