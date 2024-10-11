package com.example.buildingmaterialstore.service.srvc_Interface;

import com.example.buildingmaterialstore.model.request.UserRequestDto;
import com.example.buildingmaterialstore.model.response.UserResponseDto;

import java.util.List;

public interface UserService {
    UserResponseDto registerUser(UserRequestDto userRequestDto);
    UserResponseDto getUserById(Long id);
    UserResponseDto updateUser(Long id,UserRequestDto userRequestDto);
    void deleteUser(Long id);
    List<UserResponseDto> getAllUsers();
}
