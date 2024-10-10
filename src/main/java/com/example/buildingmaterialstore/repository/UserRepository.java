package com.example.buildingmaterialstore.repository;

import com.example.buildingmaterialstore.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByEmailOrPhoneNumber(String email,String phoneNumber);
}
