package com.example.buildingmaterialstore.model.dto.response;

import lombok.Data;

@Data
public class UserResponseDto {

    private Long id;
    private String name;
    private String surname;
    private String email;
    private String phoneNumber;
}
