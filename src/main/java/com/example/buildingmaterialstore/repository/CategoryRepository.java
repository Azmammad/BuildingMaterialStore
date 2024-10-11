package com.example.buildingmaterialstore.repository;

import com.example.buildingmaterialstore.entity.Category;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository {
    List<Category> findByName(String name);
}
