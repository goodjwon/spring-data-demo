package com.example.ordersystem.service;

import com.example.ordersystem.dto.OrderDTO;
import com.example.ordersystem.dto.OrderItemDTO;
import com.example.ordersystem.entity.Order;
import com.example.ordersystem.entity.OrderItem;
import com.example.ordersystem.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public OrderDTO convertToDto(Order order) {
        OrderDTO dto = new OrderDTO();
        dto.setId(order.getId());
        dto.setCustomerName(order.getCustomerName());
        dto.setItems(order.getItems().stream().map(this::convertToDto).collect(Collectors.toList()));
        return dto;
    }

    public OrderItemDTO convertToDto(OrderItem item) {
        OrderItemDTO dto = new OrderItemDTO();
        dto.setId(item.getId());
        dto.setProductName(item.getProductName());
        dto.setQuantity(item.getQuantity());
        return dto;
    }

    public Order convertToEntity(OrderDTO dto) {
        Order order = new Order();
        order.setId(dto.getId());
        order.setCustomerName(dto.getCustomerName());
        order.setItems(dto.getItems().stream().map(this::convertToEntity).collect(Collectors.toList()));
        return order;
    }

    public OrderItem convertToEntity(OrderItemDTO dto) {
        OrderItem item = new OrderItem();
        item.setId(dto.getId());
        item.setProductName(dto.getProductName());
        item.setQuantity(dto.getQuantity());
        return item;
    }

    @Async
    public CompletableFuture<OrderDTO> saveOrderAsync(OrderDTO orderDTO) {
        Order order = convertToEntity(orderDTO);
        Order savedOrder = orderRepository.save(order);
        return CompletableFuture.completedFuture(convertToDto(savedOrder));
    }
}
