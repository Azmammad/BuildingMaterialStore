package com.example.buildingmaterialstore.service.impl;

import com.example.buildingmaterialstore.entity.User;
import com.example.buildingmaterialstore.model.enums.UserStatus;
import com.example.buildingmaterialstore.mapper.UserMapper;
import com.example.buildingmaterialstore.model.dto.request.UserRequestDto;
import com.example.buildingmaterialstore.model.dto.response.UserResponseDto;
import com.example.buildingmaterialstore.model.exception.handle.InactiveUserException;
import com.example.buildingmaterialstore.model.exception.handle.ResourceNotFoundException;
import com.example.buildingmaterialstore.repository.UserRepository;
import com.example.buildingmaterialstore.service.srvc_Interfaces.UserService;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Data
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;


    @Override
    public UserResponseDto registerUser(UserRequestDto userRequestDto) {
        User user = userMapper.toEntity(userRequestDto);

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setStatus(UserStatus.ACTIVE);

        user = userRepository.save(user);
        userMapper.toResponse(user);

        return userMapper.toResponse(user);
    }

    @Override
    public UserResponseDto getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        if (user.getStatus() != UserStatus.ACTIVE) {
            throw new InactiveUserException(id);
        }

        return userMapper.toResponse(user);
    }

    @Override
    public UserResponseDto updateUser(Long id, UserRequestDto userRequestDto) {
        User user = userRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("User not found"));

        if (user.getStatus() == UserStatus.INACTIVE){
            throw new InactiveUserException(id);
        }

        userMapper.updateUserFromDto(userRequestDto,user);

        User updatedUser = userRepository.save(user);

        return userMapper.toResponse(updatedUser);
    }

    @Override
    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("User not found"));

        user.setStatus(UserStatus.DELETED);
        userRepository.save(user);
    }

    @Override
    public List<UserResponseDto> getAllUsers() {
        return userRepository.findAllByStatus(UserStatus.ACTIVE)
                .stream()
                .map(userMapper::toResponse)
                .collect(Collectors.toList());
    }
}
