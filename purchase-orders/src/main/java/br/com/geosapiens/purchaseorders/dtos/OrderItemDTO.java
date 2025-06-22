package br.com.geosapiens.purchaseorders.dtos;

import java.math.BigDecimal;

public record OrderItemDTO(
    Long itemIndex,
    Long productId,
    String productName,
    BigDecimal quantity,
    BigDecimal unitPrice,
    BigDecimal totalPrice
) {}
