package com.example.buildingmaterialstore.model.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProductRequestDto {

    @NotNull
    private String name;
    @NotNull
    private Double price;
    @NotNull
    private String description;
    @NotNull
    private Integer stockQuantity;
}
