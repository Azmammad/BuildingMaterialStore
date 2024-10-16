package com.example.buildingmaterialstore.model.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LoginRequestDto {
    @NotNull
    private String emailOrPhoneNumber;

    @NotNull
    private String password;
}
