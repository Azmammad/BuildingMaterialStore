package com.example.buildingmaterialstore.model.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Setter;

import java.util.List;

@Data
@Setter
public class OrderRequestDto {
    private Long cartId;
    private Long deliveryAddressId;
}