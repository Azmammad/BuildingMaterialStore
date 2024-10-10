package com.example.buildingmaterialstore.model.response;

import lombok.Data;

@Data
public class AddressResponseDto {
    private Long id;
    private String city;
    private String street;
    private String buildingNumber;
}