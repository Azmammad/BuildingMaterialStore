package com.example.buildingmaterialstore.entity;

import com.example.buildingmaterialstore.model.enums.OrderStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL)
    private List<OrderItem> orderItems;

    @NotNull
    private Double totalAmount;

    @ManyToOne
    private Address deliveryAdress;

    @Enumerated(EnumType.STRING)
    private OrderStatus status = OrderStatus.PENDING;


}
