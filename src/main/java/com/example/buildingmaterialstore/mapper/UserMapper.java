package com.example.buildingmaterialstore.mapper;

import com.example.buildingmaterialstore.entity.User;
import com.example.buildingmaterialstore.model.request.UserRequestDto;
import com.example.buildingmaterialstore.model.response.UserResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class UserMapper {

    public abstract UserResponseDto toResponse(User user);
    public abstract User toEntity(UserRequestDto userRequestDto);
}
