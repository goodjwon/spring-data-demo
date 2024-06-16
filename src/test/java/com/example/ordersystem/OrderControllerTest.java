package com.example.ordersystem.controller;

import com.example.ordersystem.dto.OrderDTO;
import com.example.ordersystem.dto.OrderItemDTO;
import com.example.ordersystem.service.OrderService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.asyncDispatch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OrderController.class)
class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderService orderService;

    @Autowired
    private ObjectMapper objectMapper;

    private OrderDTO orderDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        orderDTO = new OrderDTO();
        orderDTO.setCustomerName("John Doe");
        List<OrderItemDTO> orderItemDTOList = new ArrayList<>();
        OrderItemDTO orderItemDTO = new OrderItemDTO();
        orderItemDTO.setProductName("Product 1");
        orderItemDTO.setQuantity(2);
        orderItemDTOList.add(orderItemDTO);
        orderDTO.setItems(orderItemDTOList);
    }

    @Test
    void testCreateOrder() throws Exception {
        OrderDTO createdOrderDTO = new OrderDTO();
        createdOrderDTO.setId(1L);
        createdOrderDTO.setCustomerName("John Doe");
        createdOrderDTO.setItems(orderDTO.getItems());

        doReturn(CompletableFuture.completedFuture(createdOrderDTO))
                .when(orderService)
                .createOrder(any(OrderDTO.class));

        MvcResult mvcResult = mockMvc.perform(post("/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(orderDTO)))
                .andExpect(status().isOk())
                .andReturn();

        mockMvc.perform(asyncDispatch(mvcResult))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.customerName").value("John Doe"))
                .andExpect(jsonPath("$.items[0].productName").value("Product 1"))
                .andExpect(jsonPath("$.items[0].quantity").value(2));
    }

    @Test
    void testGetAllOrders() throws Exception {
        List<OrderDTO> orderDTOList = new ArrayList<>();
        orderDTOList.add(orderDTO);

        doReturn(CompletableFuture.completedFuture(orderDTOList))
                .when(orderService)
                .getAllOrders();

        MvcResult mvcResult = mockMvc.perform(get("/orders")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        mockMvc.perform(asyncDispatch(mvcResult))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].customerName").value("John Doe"))
                .andExpect(jsonPath("$[0].items[0].productName").value("Product 1"))
                .andExpect(jsonPath("$[0].items[0].quantity").value(2));
    }
}
