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
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Data
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;


   /* @Override
    public UserResponseDto registerUser(UserRequestDto userRequestDto) {
        log.info("-> starting user registration");

        User user = userMapper.toEntity(userRequestDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setStatus(UserStatus.ACTIVE);

        user = userRepository.save(user);

        log.info("-> user registered with id {}", user.getId());
        return userMapper.toResponse(user);
    }*/

    @Override
    public UserResponseDto getUserById(Long id) {
        log.info("-> fetching user with id {}", id);

        User user = userRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("-> user with id {} not found", id);
                    return new ResourceNotFoundException("User not found");
                });

        if (user.getStatus() != UserStatus.ACTIVE) {
            log.warn("-> inactive user with id {}", id);
            throw new InactiveUserException(id);
        }

        log.info("-> user with id {} fetched successfully", id);
        return userMapper.toResponse(user);
    }

    @Override
    public UserResponseDto updateUser(Long id, UserRequestDto userRequestDto) {
        log.info("-> updating user with id {}", id);

        User user = userRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("-> user with id {} not found", id);
                    return new ResourceNotFoundException("User not found");
                });

        if (user.getStatus() == UserStatus.INACTIVE) {
            log.warn("-> cannot update inactive user with id {}", id);
            throw new InactiveUserException(id);
        }

        userMapper.updateUserFromDto(userRequestDto, user);
        User updatedUser = userRepository.save(user);

        log.info("-> user with id {} updated successfully", id);
        return userMapper.toResponse(updatedUser);
    }

    @Override
    public void deleteUser(Long id) {
        log.info("-> deleting user with id {}", id);

        User user = userRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("-> user with id {} not found", id);
                    return new ResourceNotFoundException("User not found");
                });

        user.setStatus(UserStatus.DELETED);
        userRepository.save(user);

        log.info("-> user with id {} marked as deleted", id);
    }

    @Override
    public List<UserResponseDto> getAllUsers() {
        log.info("-> fetching all active users");

        List<UserResponseDto> activeUsers = userRepository.findAllByStatus(UserStatus.ACTIVE)
                .stream()
                .map(userMapper::toResponse)
                .collect(Collectors.toList());

        log.info("-> fetched {} active users", activeUsers.size());
        return activeUsers;
    }
}
