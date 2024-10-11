package com.example.buildingmaterialstore.model.response;

import lombok.Data;

import java.util.List;

@Data
public class CartResponseDto {

    private Long id;
    private List<CartItemResponseDto> cartItems;
    private Double totalPrice;
}
