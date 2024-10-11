package com.example.buildingmaterialstore.service.impl;

import com.example.buildingmaterialstore.entity.Category;
import com.example.buildingmaterialstore.mapper.CategoryMapper;
import com.example.buildingmaterialstore.model.dto.request.CategoryRequestDto;
import com.example.buildingmaterialstore.model.dto.response.CategoryResponseDto;
import com.example.buildingmaterialstore.repository.CategoryRepository;
import com.example.buildingmaterialstore.service.srvc_Interfaces.CategoryService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public CategoryResponseDto addCategory(CategoryRequestDto categoryRequestDto) {
        Category category = categoryMapper.toEntity(categoryRequestDto);
        category = categoryRepository.save(category);
        return categoryMapper.toResponse(category);
    }

    @Override
    public CategoryResponseDto getCategoryById(Long id) {
        return null;
    }

    @Override
    public CategoryResponseDto updateCategory(Long id, CategoryRequestDto categoryRequestDto) {
        return null;
    }

    @Override
    public void deleteCategory(Long id) {

    }

    @Override
    public List<CategoryResponseDto> getAllCategories() {
        return null;
    }
}
