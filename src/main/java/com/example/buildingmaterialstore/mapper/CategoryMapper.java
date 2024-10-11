package com.example.buildingmaterialstore.mapper;

import com.example.buildingmaterialstore.entity.Category;
import com.example.buildingmaterialstore.model.dto.request.CategoryRequestDto;
import com.example.buildingmaterialstore.model.dto.response.CategoryResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class CategoryMapper {
    public abstract CategoryResponseDto toResponse(Category category);
    public abstract Category toEntity(CategoryRequestDto categoryRequestDto);
}