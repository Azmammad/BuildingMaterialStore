package com.example.buildingmaterialstore.repository;

import com.example.buildingmaterialstore.entity.Order;
import com.example.buildingmaterialstore.entity.User;
import com.example.buildingmaterialstore.enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {

    List<Order> findByUser(User user);
    List<Order> findByStatus(OrderStatus status);
}
