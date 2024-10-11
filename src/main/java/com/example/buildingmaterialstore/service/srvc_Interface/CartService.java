package com.example.buildingmaterialstore.service.srvc_Interface;

import com.example.buildingmaterialstore.model.request.CartRequestDto;
import com.example.buildingmaterialstore.model.response.CartResponseDto;

public interface CartService {
    CartResponseDto addToCart(CartRequestDto cartRequestDto);
    CartResponseDto getCartById(Long id);
    void removeItemFromCart(Long cartId,Long productId);
}
