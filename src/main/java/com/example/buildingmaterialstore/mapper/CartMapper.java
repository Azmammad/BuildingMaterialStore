package com.example.buildingmaterialstore.mapper;

import com.example.buildingmaterialstore.entity.Cart;
import com.example.buildingmaterialstore.model.request.CartRequestDto;
import com.example.buildingmaterialstore.model.response.CartResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class CartMapper {

    public abstract CartResponseDto toResponse(Cart cart);
    public abstract Cart toEntity(CartRequestDto cartRequestDto);
}
