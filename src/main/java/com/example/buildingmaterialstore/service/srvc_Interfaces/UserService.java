package com.example.buildingmaterialstore.service.srvc_Interfaces;

import com.example.buildingmaterialstore.model.dto.request.UserRequestDto;
import com.example.buildingmaterialstore.model.dto.response.UserResponseDto;

import java.util.List;

public interface UserService {
    UserResponseDto registerUser(UserRequestDto userRequestDto);
    UserResponseDto getUserById(Long id);
    UserResponseDto updateUser(Long id,UserRequestDto userRequestDto);
    void deleteUser(Long id);
    List<UserResponseDto> getAllUsers();
}
