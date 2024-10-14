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
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;
    private final OrderMapper orderMapper;
    private final AddressRepository addressRepository;

    @Override
    public OrderResponseDto placeOrder(OrderRequestDto orderRequestDto) {
        log.info("-> placing order for cart id {}", orderRequestDto.getCartId());

        Cart cart = cartRepository.findById(orderRequestDto.getCartId())
                .orElseThrow(() -> {
                    log.error("-> cart with id {} not found", orderRequestDto.getCartId());
                    return new ResourceNotFoundException("Cart not found");
                });

        Order order = new Order();
        order.setUser(cart.getUser());
        order.setStatus(OrderStatus.PENDING);
        order.setTotalAmount(cart.calculateTotalPrice());

        Address deliveryAddress = addressRepository.findById(orderRequestDto.getDeliveryAddressId())
                .orElseThrow(() -> {
                    log.error("-> address with id {} not found", orderRequestDto.getDeliveryAddressId());
                    return new ResourceNotFoundException("Address not found");
                });

        order.setDeliveryAddress(deliveryAddress);
        order = orderRepository.save(order);

        log.info("-> order placed successfully with id {}", order.getId());
        return orderMapper.toResponse(order);
    }

    @Override
    public OrderResponseDto getOrderById(Long id) {
        log.info("-> fetching order with id {}", id);

        Order order = orderRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("-> order with id {} not found", id);
                    return new ResourceNotFoundException("Order not found");
                });

        return orderMapper.toResponse(order);
    }

    @Override
    public List<OrderResponseDto> getOrdersByUserId(Long userId) {
        log.info("-> fetching orders for user id {}", userId);

        List<OrderResponseDto> orders = orderRepository.findAllByUserId(userId)
                .stream()
                .map(orderMapper::toResponse)
                .collect(Collectors.toList());

        log.info("-> fetched {} orders for user id {}", orders.size(), userId);
        return orders;
    }

    @Override
    public void cancelOrder(Long id) {
        log.info("-> canceling order with id {}", id);

        Order order = orderRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("-> order with id {} not found", id);
                    return new ResourceNotFoundException("Order not found");
                });

        order.setStatus(OrderStatus.CANCELED);
        orderRepository.save(order);

        log.info("-> order with id {} canceled successfully", id);
    }
}
