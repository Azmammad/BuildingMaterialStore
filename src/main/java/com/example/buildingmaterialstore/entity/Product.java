package com.example.buildingmaterialstore.entity;

import com.example.buildingmaterialstore.enums.ProductStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private Double price;

    @NotNull
    private String description;

    @NotNull
    private Integer stockQuantity;

    @Enumerated(EnumType.STRING)
    private ProductStatus status = ProductStatus.ACTIVE;
}
