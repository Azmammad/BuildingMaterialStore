package com.example.buildingmaterialstore.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Table(name = "addresses")
@Data
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String city;

    @NotNull
    private String street;

    @NotNull
    @Column(name = "building_number")
    private String buildingNumber;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}