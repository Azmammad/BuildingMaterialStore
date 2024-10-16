package com.example.buildingmaterialstore.service.srvc_Interfaces;

import com.example.buildingmaterialstore.model.dto.request.LoginRequestDto;
import com.example.buildingmaterialstore.model.dto.request.UserRequestDto;
import com.example.buildingmaterialstore.model.dto.response.UserResponseDto;

public interface AuthService {
    //UserResponseDto registerUser(UserRequestDto userRequestDto);
    String login(LoginRequestDto loginRequestDto);
}
