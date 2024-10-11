package com.example.buildingmaterialstore.mapper;

import com.example.buildingmaterialstore.entity.Order;
import com.example.buildingmaterialstore.model.dto.request.OrderRequestDto;
import com.example.buildingmaterialstore.model.dto.response.OrderResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class OrderMapper {

    public abstract OrderResponseDto toResponse(Order order);
    public abstract Order toEntity(OrderRequestDto orderRequestDto);
}
