package com.example.buildingmaterialstore.repository;

import com.example.buildingmaterialstore.entity.Product;
import com.example.buildingmaterialstore.enums.ProductStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByStatus(ProductStatus status);
    List<Product> findByCategoryId(Long categoryId);
}
