package com.example.buildingmaterialstore.mapper;

import com.example.buildingmaterialstore.entity.Product;
import com.example.buildingmaterialstore.model.dto.request.ProductRequestDto;
import com.example.buildingmaterialstore.model.dto.response.ProductResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public abstract class ProductMapper {

    public abstract ProductResponseDto toResponse(Product product);
    public abstract Product toEntity(ProductRequestDto productRequestDto);
    public abstract void updateProductFromDto(ProductRequestDto productRequestDto, @MappingTarget Product product);
}
