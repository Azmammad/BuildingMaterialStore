package com.example.buildingmaterialstore.model.request;


import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class OrderItemDto {
    @NotNull
    private Long productId;
    @NotNull
    private Integer quantity;
}
