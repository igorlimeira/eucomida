package br.com.geosapiens.purchaseorders.dtos;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public record SubmitOrderDTO(
    UUID customerId,
    UUID restaurantId,
    String paymentMethod,
    String shippingAddress,
    String notes,
    BigDecimal totalAmount,
    List<OrderItemDTO> items
) {
}
