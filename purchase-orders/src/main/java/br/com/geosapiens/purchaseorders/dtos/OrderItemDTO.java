package br.com.geosapiens.purchaseorders.dtos;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;

public record OrderItemDTO(

        @NotNull(message = "Item index is required")
        @PositiveOrZero(message = "Item index must be zero or a positive number")
        Long itemIndex,

        @NotNull(message = "Product ID is required")
        Long productId,

        @NotBlank(message = "Product name is required")
        @Size(max = 100, message = "Product name must have a maximum of 100 characters")
        String productName,

        @NotNull(message = "Quantity is required")
        @DecimalMin(value = "1.00", message = "Quantity must be greater than or equal to 1")
        BigDecimal quantity,

        @NotNull(message = "Unit price is required")
        @DecimalMin(value = "0.00", inclusive = false, message = "Unit price must be greater than zero")
        BigDecimal unitPrice

) {}
