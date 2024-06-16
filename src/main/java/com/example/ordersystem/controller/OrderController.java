package com.example.ordersystem.controller;

import com.example.ordersystem.dto.OrderDTO;
import com.example.ordersystem.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public CompletableFuture<ResponseEntity<OrderDTO>> createOrder(@Validated @RequestBody OrderDTO orderDTO) {
        return orderService.createOrder(orderDTO).thenApply(ResponseEntity::ok);
    }

    @GetMapping
    public CompletableFuture<ResponseEntity<List<OrderDTO>>> getAllOrders() {
        return orderService.getAllOrders().thenApply(ResponseEntity::ok);
    }
}
