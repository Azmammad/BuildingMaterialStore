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
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;
    private final CartMapper cartMapper;

    @Override
    public CartResponseDto addToCart(CartRequestDto cartRequestDto) {
        log.info("-> Adding cart with items {}", cartRequestDto.getProductId());

        Cart cart = cartMapper.toEntity(cartRequestDto);
        cart = cartRepository.save(cart);

        log.info("-> Cart added successfully with id {}", cart.getId());
        return cartMapper.toResponse(cart);
    }

    @Override
    public CartResponseDto getCartById(Long id) {
        log.info("-> Fetching cart with id {}", id);

        Cart cart = cartRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("-> Cart with id {} not found", id);
                    return new ResourceNotFoundException("Cart not found");
                });

        return cartMapper.toResponse(cart);
    }

    @Override
    public void removeItemFromCart(Long cartId, Long productId) {
        log.info("-> Removing item with product id {} from cart id {}", productId, cartId);

        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> {
                    log.error("-> Cart with id {} not found", cartId);
                    return new ResourceNotFoundException("Cart not found");
                });

        CartItem cartItem = cartItemRepository.findById(productId)
                .orElseThrow(() -> {
                    log.error("-> Product with id {} not found", productId);
                    return new ResourceNotFoundException("Product not found");
                });

        cart.getCartItems().remove(cartItem);
        cartItemRepository.delete(cartItem);

        double totalPrice = cart.getTotalPrice() - (cartItem.getProduct().getPrice() * cartItem.getQuantity());
        cart.setTotalPrice(totalPrice);

        cartRepository.save(cart);
        log.info("-> Item with product id {} removed from cart id {}", productId, cartId);
    }

    @Override
    public void removeAllItemsFromCart(Long cartId) {
        log.info("-> Removing all items from cart id {}", cartId);

        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> {
                    log.error("-> Cart with id {} not found", cartId);
                    return new ResourceNotFoundException("Cart not found");
                });

        cart.getCartItems().clear();
        cart.setTotalPrice(0.0);
        cartRepository.save(cart);

        log.info("-> All items removed from cart id {}", cartId);
    }

    @Override
    public void decreaseProductQuantityInCart(Long cartId, Long productId, int quantity) {
        log.info("-> Decreasing quantity of product id {} in cart id {} by {}", productId, cartId, quantity);

        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> {
                    log.error("-> Cart with id {} not found", cartId);
                    return new ResourceNotFoundException("Cart not found");
                });

        CartItem cartItem = cartItemRepository.findByCartAndProductId(cart, productId)
                .orElseThrow(() -> {
                    log.error("-> Product with id {} not found in cart id {}", productId, cartId);
                    return new ResourceNotFoundException("Product not found in this cart");
                });

        if (cartItem.getQuantity() > quantity) {
            cartItem.setQuantity(cartItem.getQuantity() - quantity);
            cart.setTotalPrice(cart.getTotalPrice() - (cartItem.getProduct().getPrice() * quantity));
            cartItemRepository.save(cartItem);
            log.info("-> Decreased quantity of product id {} in cart id {} successfully", productId, cartId);
        } else {
            removeItemFromCart(cartId, productId);
        }

        cartRepository.save(cart);
    }
}
