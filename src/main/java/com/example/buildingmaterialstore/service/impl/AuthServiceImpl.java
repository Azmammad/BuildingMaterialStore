package com.example.buildingmaterialstore.service.impl;

import com.example.buildingmaterialstore.config.JwtTokenUtil;
import com.example.buildingmaterialstore.entity.User;
import com.example.buildingmaterialstore.model.dto.request.LoginRequestDto;
import com.example.buildingmaterialstore.repository.UserRepository;
import com.example.buildingmaterialstore.service.srvc_Interfaces.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final JwtTokenUtil jwtTokenUtil;
    private final PasswordEncoder passwordEncoder;

    @Override
    public String login(LoginRequestDto loginRequestDto) {
        Optional<User> userOptional = userRepository.findByEmail(loginRequestDto.getEmailOrPhoneNumber());

        if (userOptional.isEmpty()){
            userOptional = userRepository.findByPhoneNumber(loginRequestDto.getEmailOrPhoneNumber());
        }

        User user = userOptional.orElseThrow(()-> new RuntimeException("Invalid credentials"));

        if (!passwordEncoder.matches(loginRequestDto.getPassword(),user.getPassword())){
            throw new RuntimeException("Invalid credentials");
        }

        return jwtTokenUtil.generateToken(user.getEmail());
    }

}
