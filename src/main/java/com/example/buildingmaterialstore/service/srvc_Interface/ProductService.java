package com.example.buildingmaterialstore.service.srvc_Interface;

import com.example.buildingmaterialstore.model.dto.request.ProductRequestDto;
import com.example.buildingmaterialstore.model.dto.response.ProductResponseDto;

import java.util.List;

public interface ProductService {
    ProductResponseDto addProduct(ProductRequestDto productRequestDto);
    ProductResponseDto updateProduct(Long id,ProductRequestDto productRequestDto);
    void deleteProduct(Long id);
    List<ProductResponseDto> getAllProducts();
    List<ProductResponseDto> getProductsByCategory(Long categoryID);
}
