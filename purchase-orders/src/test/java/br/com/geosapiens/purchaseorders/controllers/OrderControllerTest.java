package br.com.geosapiens.purchaseorders.controllers;

import br.com.geosapiens.exceptions.GlobalExceptionHandler;
import br.com.geosapiens.purchaseorders.dtos.ResponseOrderDTO;
import br.com.geosapiens.purchaseorders.dtos.SubmitOrderDTO;
import br.com.geosapiens.purchaseorders.enums.EOrderError;
import br.com.geosapiens.purchaseorders.enums.EOrderStatus;
import br.com.geosapiens.purchaseorders.exceptions.OrderException;
import br.com.geosapiens.purchaseorders.interfaces.OrderServiceInterface;
import br.com.geosapiens.purchaseorders.utils.OrderMockUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(OrderController.class)
@Import({GlobalExceptionHandler.class, OrderControllerTest.MockBeans.class})
class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private OrderServiceInterface orderService;

    @BeforeEach
    void setup() {
        Mockito.reset(orderService);
    }

    @TestConfiguration
    static class MockBeans {
        @Bean
        public OrderServiceInterface orderService() {
            return Mockito.mock(OrderServiceInterface.class);
        }
    }

    @Test
    void shouldReturnCreatedOrder() throws Exception {
        SubmitOrderDTO request = OrderMockUtils.mockNewOrder();

        Mockito.when(orderService.submitOrder(any()))
                .thenReturn(ResponseOrderDTO.builder()
                        .orderId(1L)
                        .orderStatus(EOrderStatus.PENDING)
                        .customerId(request.customerId())
                        .restaurantId(request.restaurantId())
                        .createdAt(LocalDateTime.now())
                        .paymentMethod(request.paymentMethod())
                        .build()
                );

        mockMvc.perform(post("/api/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.orderId").exists())
                .andExpect(jsonPath("$.orderStatus").value(EOrderStatus.PENDING.name()));
    }

    @Test
    void shouldReturnBadRequestDataIntegrityViolationException() throws Exception {
        SubmitOrderDTO request = new SubmitOrderDTO(
                UUID.randomUUID(),
                UUID.randomUUID(),
                "CREDIT_CARD",
                "123 Main Street",
                "no onions please",
                null,
                Collections.emptyList()
        );

        Mockito.when(orderService.submitOrder(any()))
                .thenThrow(new OrderException(EOrderError.INVALID_ORDER));

        mockMvc.perform(post("/api/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value(EOrderError.INVALID_ORDER.code()))
                .andExpect(jsonPath("$.message").value(EOrderError.INVALID_ORDER.message()));
    }


}
