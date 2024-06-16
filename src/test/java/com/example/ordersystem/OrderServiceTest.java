package com.example.ordersystem;

import com.example.ordersystem.dto.OrderDTO;
import com.example.ordersystem.dto.OrderItemDTO;
import com.example.ordersystem.entity.Order;
import com.example.ordersystem.repository.OrderRepository;
import com.example.ordersystem.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderService orderService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateOrder() throws ExecutionException, InterruptedException {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setCustomerName("John Doe");
        List<OrderItemDTO> orderItemDTOList = new ArrayList<>();
        OrderItemDTO orderItemDTO = new OrderItemDTO();
        orderItemDTO.setProductName("Product 1");
        orderItemDTO.setQuantity(2);
        orderItemDTOList.add(orderItemDTO);
        orderDTO.setItems(orderItemDTOList);

        Order order = new Order();
        order.setId(1L);
        order.setCustomerName("John Doe");

        when(orderRepository.save(any(Order.class))).thenReturn(order);

        CompletableFuture<OrderDTO> createdOrderFuture = orderService.createOrder(orderDTO);
        OrderDTO createdOrderDTO = createdOrderFuture.get();

        assertEquals(1L, createdOrderDTO.getId());
        assertEquals("John Doe", createdOrderDTO.getCustomerName());
    }
}
