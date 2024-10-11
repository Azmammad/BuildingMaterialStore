package com.example.buildingmaterialstore.model.dto.response;

import com.example.buildingmaterialstore.model.enums.ProductStatus;
import lombok.Data;

@Data
public class ProductResponseDto {

    private Long id;
    private String name;
    private Double price;
    private String description;
    private Integer stockQuantity;
    private ProductStatus status;
}
