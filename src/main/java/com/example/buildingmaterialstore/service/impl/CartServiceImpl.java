package com.example.buildingmaterialstore.service.impl;

import com.example.buildingmaterialstore.entity.Cart;
import com.example.buildingmaterialstore.entity.CartItem;
import com.example.buildingmaterialstore.mapper.CartMapper;
import com.example.buildingmaterialstore.model.dto.request.CartRequestDto;
import com.example.buildingmaterialstore.model.dto.response.CartResponseDto;
import com.example.buildingmaterialstore.model.exception.handle.ResourceNotFoundException;
import com.example.buildingmaterialstore.repository.CartItemRepository;
import com.example.buildingmaterialstore.repository.CartRepository;
import com.example.buildingmaterialstore.repository.ProductRepository;
import com.example.buildingmaterialstore.service.srvc_Interfaces.CartService;
import lombok.Data;
import org.springframework.stereotype.Service;

@Service
@Data
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;
    private final CartMapper cartMapper;


    @Override
    public CartResponseDto addToCart(CartRequestDto cartRequestDto) {
        Cart cart = cartMapper.toEntity(cartRequestDto);

        cartRepository.save(cart);
        return cartMapper.toResponse(cart);
    }

    @Override
    public CartResponseDto getCartById(Long id) {
        Cart cart = cartRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Cart not found"));

        return cartMapper.toResponse(cart);
    }

    @Override
    public void removeItemFromCart(Long cartId, Long productId) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(()->new ResourceNotFoundException("Cart not found"));

        CartItem cartItem = cartItemRepository.findById(productId)
                .orElseThrow(()->new ResourceNotFoundException("Product not found"));

        cart.getCartItems().remove(cartItem);
        cartItemRepository.delete(cartItem);

        cart.setTotalPrice(cart.getTotalPrice()-(cartItem.getProduct().getPrice()*cartItem.getQuantity()));

        cartRepository.save(cart);
    }

    @Override
    public void removeAllItemsFromCart(Long cartId) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(()->new ResourceNotFoundException("Cart not found"));

        cart.getCartItems().clear();
        cart.setTotalPrice(0.0);
        cartRepository.save(cart);
    }

    @Override
    public void decreaseProductQuantityInCart(Long cartId, Long productId, int quantity) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(()->new ResourceNotFoundException("Cart not found"));

        CartItem cartItem = cartItemRepository.findByCartAndProductId(cart,productId)
                .orElseThrow(()->new ResourceNotFoundException("Product not found this cart"));

        if (cartItem.getQuantity()>quantity){
            cartItem.setQuantity(cartItem.getQuantity()-quantity);

            cart.setTotalPrice(cart.getTotalPrice() - (cartItem.getProduct().getPrice()*quantity));

            cartItemRepository.save(cartItem);
        }else {
            removeItemFromCart(cartId,productId);
        }

        cartRepository.save(cart);
    }


}
