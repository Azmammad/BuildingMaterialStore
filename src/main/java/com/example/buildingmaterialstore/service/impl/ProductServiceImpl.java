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
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductMapper productMapper;

    @Override
    public ProductResponseDto addProduct(ProductRequestDto productRequestDto) {
        log.info("-> adding new product");

        Product product = productMapper.toEntity(productRequestDto);
        product.setStockQuantity(1);
        product = productRepository.save(product);

        log.info("-> product added with id {}", product.getId());
        return productMapper.toResponse(product);
    }

    @Override
    public ProductResponseDto updateProduct(Long id, ProductRequestDto productRequestDto) {
        log.info("-> updating product with id {}", id);

        Product product = productRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("-> product with id {} not found", id);
                    return new ResourceNotFoundException("Product not found");
                });

        productMapper.updateProductFromDto(productRequestDto, product);
        Product updatedProduct = productRepository.save(product);

        log.info("-> product with id {} updated successfully", updatedProduct.getId());
        return productMapper.toResponse(updatedProduct);
    }

    @Override
    public void deleteProduct(Long id) {
        log.info("-> deleting product with id {}", id);

        Product product = productRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("-> product with id {} not found", id);
                    return new ResourceNotFoundException("Product not found");
                });

        productRepository.delete(product);
        log.info("-> product with id {} deleted successfully", id);
    }

    @Override
    public List<ProductResponseDto> getAllProducts() {
        log.info("-> fetching all products");

        List<ProductResponseDto> products = productRepository.findAll()
                .stream()
                .map(productMapper::toResponse)
                .collect(Collectors.toList());

        log.info("-> fetched {} products", products.size());
        return products;
    }

    @Override
    public List<ProductResponseDto> getProductsByCategory(Long categoryID) {
        log.info("-> fetching products for category id {}", categoryID);

        Category category = categoryRepository.findById(categoryID)
                .orElseThrow(() -> {
                    log.error("-> category with id {} not found", categoryID);
                    return new ResourceNotFoundException("Category not found");
                });

        List<ProductResponseDto> products = productRepository.findByCategoryId(categoryID)
                .stream()
                .map(productMapper::toResponse)
                .collect(Collectors.toList());

        log.info("-> fetched {} products for category id {}", products.size(), categoryID);
        return products;
    }
}
