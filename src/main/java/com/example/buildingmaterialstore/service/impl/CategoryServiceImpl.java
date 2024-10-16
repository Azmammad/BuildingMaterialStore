    package com.example.buildingmaterialstore.service.impl;

    import com.example.buildingmaterialstore.entity.Category;
    import com.example.buildingmaterialstore.mapper.CategoryMapper;
    import com.example.buildingmaterialstore.model.dto.request.CategoryRequestDto;
    import com.example.buildingmaterialstore.model.dto.response.CategoryResponseDto;
    import com.example.buildingmaterialstore.model.exception.handle.ResourceNotFoundException;
    import com.example.buildingmaterialstore.repository.CategoryRepository;
    import com.example.buildingmaterialstore.service.srvc_Interfaces.CategoryService;
    import lombok.RequiredArgsConstructor;
    import lombok.extern.slf4j.Slf4j;
    import org.springframework.stereotype.Service;

    import java.util.List;
    import java.util.stream.Collectors;

    @Service
    @RequiredArgsConstructor
    @Slf4j
    public class CategoryServiceImpl implements CategoryService {

        private final CategoryRepository categoryRepository;
        private final CategoryMapper categoryMapper;

        @Override
        public CategoryResponseDto addCategory(CategoryRequestDto categoryRequestDto) {
            log.info("-> adding category with name {}", categoryRequestDto.getName());

            Category category = categoryMapper.toEntity(categoryRequestDto);
            category = categoryRepository.save(category);

            log.info("-> category added successfully with id {}", category.getId());
            return categoryMapper.toResponse(category);
        }

        @Override
        public CategoryResponseDto getCategoryById(Long id) {
            log.info("-> fetching category with id {}", id);

            Category category = categoryRepository.findById(id)
                    .orElseThrow(() -> {
                        log.error("-> category with id {} not found", id);
                        return new ResourceNotFoundException("Category not found");
                    });

            return categoryMapper.toResponse(category);
        }

        @Override
        public CategoryResponseDto updateCategory(Long id, CategoryRequestDto categoryRequestDto) {
            log.info("-> updating category with id {}", id);

            Category category = categoryRepository.findById(id)
                    .orElseThrow(() -> {
                        log.error("-> category with id {} not found", id);
                        return new ResourceNotFoundException("Category not found");
                    });

            category.setName(categoryRequestDto.getName());
            category = categoryRepository.save(category);

            log.info("-> category with id {} updated successfully", id);
            return categoryMapper.toResponse(category);
        }

        @Override
        public void deleteCategory(Long id) {
            log.info("-> deleting category with id {}", id);

            Category category = categoryRepository.findById(id)
                    .orElseThrow(() -> {
                        log.error("-> category with id {} not found", id);
                        return new ResourceNotFoundException("Category not found");
                    });

            categoryRepository.delete(category);
            log.info("-> category with id {} deleted successfully", id);
        }

        @Override
        public List<CategoryResponseDto> getAllCategories() {
            log.info("-> fetching all categories");

            List<Category> categories = categoryRepository.findAll();
            List<CategoryResponseDto> categoryResponses = categories.stream()
                    .map(categoryMapper::toResponse)
                    .collect(Collectors.toList());

            log.info("-> fetched {} categories", categoryResponses.size());
            return categoryResponses;
        }
    }
