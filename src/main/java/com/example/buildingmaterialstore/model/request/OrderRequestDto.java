package com.example.buildingmaterialstore.model.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class OrderRequestDto {
    private Long cartId;
    private Long deliveryAddressId;
}