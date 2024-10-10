package com.example.buildingmaterialstore.model.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserRequestDto {

    @NotNull
    private String name;

    @NotNull
    private String surname;

    @NotNull
    @Email
    private String email;

    @NotNull
    private String phoneNumber;

    @NotNull
    private String password;
}
