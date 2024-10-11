package com.example.buildingmaterialstore.mapper;

import com.example.buildingmaterialstore.entity.User;
import com.example.buildingmaterialstore.model.dto.request.UserRequestDto;
import com.example.buildingmaterialstore.model.dto.response.UserResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public abstract class UserMapper {

    public abstract UserResponseDto toResponse(User user);
    public abstract User toEntity(UserRequestDto userRequestDto);
    public abstract void updateUserFromDto(UserRequestDto userRequestDto, @MappingTarget User user);
}
