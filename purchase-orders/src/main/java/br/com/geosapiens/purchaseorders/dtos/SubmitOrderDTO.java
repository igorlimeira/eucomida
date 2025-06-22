package br.com.geosapiens.purchaseorders.dtos;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import java.util.List;
import java.util.UUID;

public record SubmitOrderDTO(

        @NotNull(message = "Customer ID is required")
        UUID customerId,

        @NotNull(message = "Restaurant ID is required")
        UUID restaurantId,

        @NotBlank(message = "Payment method is required")
        String paymentMethod,

        @NotBlank(message = "Shipping address is required")
        String shippingAddress,

        @Size(max = 255, message = "Notes must have a maximum of 255 characters")
        String notes,

        @NotEmpty(message = "Order must contain at least one item")
        @Valid
        List<@Valid OrderItemDTO> items

) {}
