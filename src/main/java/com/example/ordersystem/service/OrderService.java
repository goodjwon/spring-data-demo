package com.example.ordersystem.service;

import com.example.ordersystem.dto.OrderDTO;
import com.example.ordersystem.dto.OrderItemDTO;
import com.example.ordersystem.entity.Order;
import com.example.ordersystem.entity.OrderItem;
import com.example.ordersystem.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    @Transactional
    public CompletableFuture<OrderDTO> createOrder(OrderDTO orderDTO) {
        return CompletableFuture.supplyAsync(() -> {
            Order order = convertToEntity(orderDTO);
            order = orderRepository.save(order);
            return convertToDto(order);
        });
    }

    @Transactional(readOnly = true)
    public CompletableFuture<List<OrderDTO>> getAllOrders() {
        return CompletableFuture.supplyAsync(() -> {
            List<OrderDTO> orders = orderRepository.findAll().stream()
                    .map(this::convertToDto)
                    .collect(Collectors.toList());
            return orders;
        });
    }

    private OrderDTO convertToDto(Order order) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(order.getId());
        orderDTO.setCustomerName(order.getCustomerName());
        orderDTO.setItems(order.getItems() != null ? order.getItems().stream().map(this::convertToDto).collect(Collectors.toList()) : new ArrayList<>());
        return orderDTO;
    }

    private OrderItemDTO convertToDto(OrderItem orderItem) {
        OrderItemDTO orderItemDTO = new OrderItemDTO();
        orderItemDTO.setId(orderItem.getId());
        orderItemDTO.setProductName(orderItem.getProductName());
        orderItemDTO.setQuantity(orderItem.getQuantity());
        return orderItemDTO;
    }

    private Order convertToEntity(OrderDTO orderDTO) {
        Order order = new Order();
        order.setId(orderDTO.getId());
        order.setCustomerName(orderDTO.getCustomerName());
        order.setItems(orderDTO.getItems() != null ? orderDTO.getItems().stream().map(this::convertToEntity).collect(Collectors.toList()) : new ArrayList<>());
        return order;
    }

    private OrderItem convertToEntity(OrderItemDTO orderItemDTO) {
        OrderItem orderItem = new OrderItem();
        orderItem.setId(orderItemDTO.getId());
        orderItem.setProductName(orderItemDTO.getProductName());
        orderItem.setQuantity(orderItemDTO.getQuantity());
        return orderItem;
    }
}
