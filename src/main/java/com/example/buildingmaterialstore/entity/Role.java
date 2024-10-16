package com.example.buildingmaterialstore.entity;

import com.example.buildingmaterialstore.model.enums.ERole;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(length = 20,unique = true)
    private ERole name;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users = new HashSet<>();
}

