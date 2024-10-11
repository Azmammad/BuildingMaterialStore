package com.example.buildingmaterialstore.model.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AddressRequestDto {
    @NotNull
    private String city;

    @NotNull
    private String street;

    @NotNull
    private String buildingNumber;
}