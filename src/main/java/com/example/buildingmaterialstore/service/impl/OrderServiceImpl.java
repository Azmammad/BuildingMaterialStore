package com.example.buildingmaterialstore.service.impl;

import com.example.buildingmaterialstore.entity.Address;
import com.example.buildingmaterialstore.entity.Cart;
import com.example.buildingmaterialstore.entity.Order;
import com.example.buildingmaterialstore.mapper.OrderMapper;
import com.example.buildingmaterialstore.model.dto.request.OrderRequestDto;
import com.example.buildingmaterialstore.model.dto.response.OrderResponseDto;
import com.example.buildingmaterialstore.model.enums.OrderStatus;
import com.example.buildingmaterialstore.model.exception.handle.ResourceNotFoundException;
import com.example.buildingmaterialstore.repository.AddressRepository;
import com.example.buildingmaterialstore.repository.CartRepository;
import com.example.buildingmaterialstore.repository.OrderRepository;
import com.example.buildingmaterialstore.service.srvc_Interfaces.OrderService;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Data
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;
    private final OrderMapper orderMapper;
    private final AddressRepository addressRepository;

    @Override
    public OrderResponseDto placeOrder(OrderRequestDto orderRequestDto) {
        Cart cart = cartRepository.findById(orderRequestDto.getCartId())
                .orElseThrow(()->new ResourceNotFoundException("Cart not found"));

        Order order = new Order();
        order.setUser(cart.getUser());
        order.setStatus(OrderStatus.PENDING);
        order.setTotalAmount(cart.calculateTotalPrice());

        Address deliveryAddress = addressRepository.findById(orderRequestDto.getDeliveryAddressId())
                .orElseThrow(()->new ResourceNotFoundException("Address not found"));

        order.setDeliveryAddress(deliveryAddress);

        order = orderRepository.save(order);
        return orderMapper.toResponse(order);
    }

    @Override
    public OrderResponseDto getOrderById(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Order not found"));

        return orderMapper.toResponse(order);
    }

    @Override
    public List<OrderResponseDto> getOrdersByUserId(Long userId) {
        return orderRepository.findAllByUserId(userId)
                .stream()
                .map(orderMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void cancelOrder(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Order not found"));

        order.setStatus(OrderStatus.CANCELED);
        orderRepository.save(order);

    }
}
