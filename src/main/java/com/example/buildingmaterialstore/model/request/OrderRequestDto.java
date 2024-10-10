package com.example.buildingmaterialstore.model.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class OrderRequestDto {
    @NotNull
    private Long addressId;
    @NotNull
    private List<OrderItemDto> items;
}
