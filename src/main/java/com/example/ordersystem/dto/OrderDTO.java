package com.example.ordersystem.dto;

import lombok.Data;

import jakarta.validation.constraints.NotEmpty;
import java.util.List;

@Data
public class OrderDTO {

    private Long id;

    @NotEmpty(message = "Customer name cannot be empty")
    private String customerName;

    @NotEmpty(message = "Order items cannot be empty")
    private List<OrderItemDTO> items;
}
