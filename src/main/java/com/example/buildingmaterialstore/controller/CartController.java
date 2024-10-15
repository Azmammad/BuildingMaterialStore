package com.example.buildingmaterialstore.controller;

import com.example.buildingmaterialstore.model.dto.request.CartRequestDto;
import com.example.buildingmaterialstore.model.dto.response.CartResponseDto;
import com.example.buildingmaterialstore.service.srvc_Interfaces.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carts")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @PostMapping
    public ResponseEntity<CartResponseDto> addToCart(@RequestBody CartRequestDto cartRequestDto) {
        CartResponseDto response = cartService.addToCart(cartRequestDto);
        return ResponseEntity.status(201).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CartResponseDto> getById(@PathVariable Long id) {
        CartResponseDto response = cartService.getCartById(id);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{cartId}/items/{productId}")
    public ResponseEntity<Void> removeItemFromCart(@PathVariable Long cartId, @PathVariable Long productId) {
        cartService.removeItemFromCart(cartId, productId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{cartId}/items")
    public ResponseEntity<Void> removeAllItemsFromCart(@PathVariable Long cartId) {
        cartService.removeAllItemsFromCart(cartId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{cartId}/items/{productId}/decrease/{quantity}")
    public ResponseEntity<Void> decreaseProductQuantityInCart(@PathVariable Long cartId, @PathVariable Long productId,
                                                              @PathVariable int quantity) {
        cartService.decreaseProductQuantityInCart(cartId, productId, quantity);
        return ResponseEntity.noContent().build();
    }
}
