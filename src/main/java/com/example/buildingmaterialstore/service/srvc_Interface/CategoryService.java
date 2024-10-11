package com.example.buildingmaterialstore.service.srvc_Interface;

import com.example.buildingmaterialstore.model.request.CategoryRequestDto;
import com.example.buildingmaterialstore.model.response.CategoryResponseDto;

import java.util.LinkedList;
import java.util.List;

public interface CategoryService {
    CategoryResponseDto addCategory(CategoryRequestDto categoryRequestDto);
    CategoryResponseDto getCategoryById(Long id);
    CategoryResponseDto updateCategory(Long id, CategoryRequestDto categoryRequestDto);
    void deleteCategory(Long id);
    List<CategoryResponseDto> getAllCategories();
}
