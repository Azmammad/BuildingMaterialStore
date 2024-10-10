package com.example.buildingmaterialstore.entity;

import com.example.buildingmaterialstore.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "payments")
@Data
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Order order;

    @Enumerated(EnumType.STRING)
    private PaymentStatus status = PaymentStatus.PENDING;

    @Enumerated(EnumType.STRING)
    private PaymentMethod method;

    private String transactionId;



    public enum PaymentMethod {
        CARD,
        CASH
    }
}

