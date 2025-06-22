package br.com.geosapiens.purchaseorders.dtos;

import br.com.geosapiens.purchaseorders.enums.EOrderStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Builder
@Getter
@Setter
public class ResponseOrderDTO {
    private UUID customerId;
    private UUID restaurantId;
    private Long orderId;
    private LocalDateTime createdAt;
    private LocalDateTime deliveredAt;
    private String paymentMethod;
    private String shippingAddress;
    private String notes;
    private BigDecimal totalAmount;
    private EOrderStatus orderStatus;
    List<ResponseOrderItemDTO> items;
}
