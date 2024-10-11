package com.example.buildingmaterialstore.service.impl;

import com.example.buildingmaterialstore.entity.Category;
import com.example.buildingmaterialstore.entity.Product;
import com.example.buildingmaterialstore.mapper.ProductMapper;
import com.example.buildingmaterialstore.model.dto.request.ProductRequestDto;
import com.example.buildingmaterialstore.model.dto.response.ProductResponseDto;
import com.example.buildingmaterialstore.model.exception.handle.ResourceNotFoundException;
import com.example.buildingmaterialstore.repository.CategoryRepository;
import com.example.buildingmaterialstore.repository.ProductRepository;
import com.example.buildingmaterialstore.service.srvc_Interfaces.ProductService;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Data
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductMapper productMapper;

    @Override
    public ProductResponseDto addProduct(ProductRequestDto productRequestDto) {
        Product product = productMapper.toEntity(productRequestDto);

        product.setStockQuantity(1);
        product = productRepository.save(product);

        return productMapper.toResponse(product);
    }

    @Override
    public ProductResponseDto updateProduct(Long id, ProductRequestDto productRequestDto) {
        Product product = productRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Product not found"));

        productMapper.updateProductFromDto(productRequestDto,product);

        Product updatedProduct = productRepository.save(product);

        return productMapper.toResponse(updatedProduct);
    }

    @Override
    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Product not found"));

        productRepository.delete(product);
    }

    @Override
    public List<ProductResponseDto> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(productMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductResponseDto> getProductsByCategory(Long categoryID) {
        Category category = categoryRepository.findById(categoryID)
                .orElseThrow(()->new ResourceNotFoundException("Category not found"));

        return productRepository.findByCategoryId(categoryID)
                .stream()
                .map(productMapper::toResponse)
                .collect(Collectors.toList());
    }
}
