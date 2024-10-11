package com.example.buildingmaterialstore.repository;

import com.example.buildingmaterialstore.entity.Cart;
import com.example.buildingmaterialstore.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem,Long> {
    Optional<CartItem> findByCartAndProductId(Cart cart, Long productId);
}
