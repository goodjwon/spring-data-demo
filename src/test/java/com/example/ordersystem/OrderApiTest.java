package com.example.ordersystem;

import com.example.ordersystem.dto.OrderDTO;
import com.example.ordersystem.dto.OrderItemDTO;
import com.example.ordersystem.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class OrderApiTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderRepository orderRepository;

    @Test
    public void testCreateOrder() throws Exception {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setCustomerName("John Doe");

        OrderItemDTO itemDTO1 = new OrderItemDTO();
        itemDTO1.setProductName("Product 1");
        itemDTO1.setQuantity(2);

        OrderItemDTO itemDTO2 = new OrderItemDTO();
        itemDTO2.setProductName("Product 2");
        itemDTO2.setQuantity(3);

        orderDTO.setItems(Arrays.asList(itemDTO1, itemDTO2));

        when(orderRepository.save(any())).thenReturn(null);

        mockMvc.perform(post("/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"customerName\":\"John Doe\",\"items\":[{\"productName\":\"Product 1\",\"quantity\":2},{\"productName\":\"Product 2\",\"quantity\":3}]}"))
                .andExpect(status().isCreated());
    }
}
