package com.example.buildingmaterialstore.mapper;

import com.example.buildingmaterialstore.entity.Product;
import com.example.buildingmaterialstore.model.request.ProductRequestDto;
import com.example.buildingmaterialstore.model.response.ProductResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class ProductMapper {

    public abstract ProductResponseDto toResponse(Product product);
    public abstract Product toEntity(ProductRequestDto productRequestDto);

}
