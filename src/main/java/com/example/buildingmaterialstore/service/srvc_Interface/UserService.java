package com.example.buildingmaterialstore.service.srvc_Interface;

import com.example.buildingmaterialstore.model.request.UserRequestDto;
import com.example.buildingmaterialstore.model.response.UserResponseDto;

import java.util.List;

public interface UserService {
    UserResponseDto createUser(UserRequestDto userRequestDto);
    UserResponseDto getUserById(Long id);
    List<UserResponseDto> getAllUsers();
    void deleteUser(Long id);
}
