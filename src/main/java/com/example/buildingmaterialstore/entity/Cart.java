package com.example.buildingmaterialstore.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "carts")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private User user;

    @OneToMany(mappedBy = "cart",cascade = CascadeType.ALL)
    private List<CartItem> cartItems;

    private Double totalPrice;

    public Double calculateTotalPrice(){
        return cartItems.stream()
                .mapToDouble(cartItem -> cartItem.getProduct().getPrice()*cartItem.getQuantity())
                .sum();
    }

}
