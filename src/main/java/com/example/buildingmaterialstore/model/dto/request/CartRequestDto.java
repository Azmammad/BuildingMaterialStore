package com.example.buildingmaterialstore.model.dto.request;


import lombok.Data;

@Data
public class CartRequestDto {
    private Long productId;
    private Integer quantity;
}