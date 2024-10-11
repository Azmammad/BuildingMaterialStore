package com.example.buildingmaterialstore.service.impl;

import com.example.buildingmaterialstore.entity.User;
import com.example.buildingmaterialstore.mapper.UserMapper;
import com.example.buildingmaterialstore.model.request.UserRequestDto;
import com.example.buildingmaterialstore.model.response.UserResponseDto;
import com.example.buildingmaterialstore.repository.UserRepository;
import com.example.buildingmaterialstore.service.srvc_Interface.UserService;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;


    @Override
    public UserResponseDto registerUser(UserRequestDto userRequestDto) {
        User user = userMapper.toEntity(userRequestDto);
        return null;
    }

    @Override
    public UserResponseDto getUserById(Long id) {
        return null;
    }

    @Override
    public UserResponseDto updateUser(Long id, UserRequestDto userRequestDto) {
        return null;
    }

    @Override
    public void deleteUser(Long id) {

    }

    @Override
    public List<UserResponseDto> getAllUsers() {
        return null;
    }
}
