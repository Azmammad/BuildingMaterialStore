package com.example.buildingmaterialstore.mapper;

import com.example.buildingmaterialstore.entity.Address;
import com.example.buildingmaterialstore.model.dto.request.AddressRequestDto;
import com.example.buildingmaterialstore.model.dto.response.AddressResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class AddressMapper {

    public abstract AddressResponseDto toResponse(Address address);
    public abstract Address toEntity(AddressRequestDto addressRequestDto);

}
