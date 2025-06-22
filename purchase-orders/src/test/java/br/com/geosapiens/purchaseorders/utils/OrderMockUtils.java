package br.com.geosapiens.purchaseorders.utils;

import br.com.geosapiens.purchaseorders.dtos.OrderItemDTO;
import br.com.geosapiens.purchaseorders.dtos.SubmitOrderDTO;
import br.com.geosapiens.purchaseorders.entities.Order;
import br.com.geosapiens.purchaseorders.entities.OrderItem;
import br.com.geosapiens.purchaseorders.entities.OrderItemID;
import br.com.geosapiens.purchaseorders.enums.EOrderStatus;
import br.com.geosapiens.utils.UtilBigDecimal;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class OrderMockUtils {

    public static Order mockNewOrderSaved(SubmitOrderDTO newMockedOrder) {
        return Order.builder()
                .id(1L)
                .customerId(newMockedOrder.customerId())
                .restaurantId(newMockedOrder.restaurantId())
                .createdDate(LocalDateTime.now())
                .shippingAddress(newMockedOrder.shippingAddress())
                .notes(newMockedOrder.notes())
                .totalAmount(newMockedOrder.totalAmount())
                .items(mocknewOrderItems(newMockedOrder))
                .status(EOrderStatus.PENDING)
                .build();
    }

    private static List<OrderItem> mocknewOrderItems(SubmitOrderDTO submitOrderDTO) {
        return submitOrderDTO.items().stream()
                .map(item -> OrderItem.builder()
                        .id(OrderItemID.builder()
                                .itemIdx(item.itemIndex())
                                .build())
                        .itemId(item.productId())
                        .name(item.productName())
                        .quantity(item.quantity())
                        .unitPrice(item.unitPrice())
                        .totalPrice(UtilBigDecimal.multiply(item.unitPrice(), item.quantity()))
                        .build())
                .toList();
    }

    public static SubmitOrderDTO mockNewOrder() {
        return new SubmitOrderDTO(
                UUID.randomUUID(), // customerId
                UUID.randomUUID(), // restaurantId
                "CREDIT_CARD", // paymentMethod
                "123 Main St, City, Country", // shippingAddress
                "Please deliver quickly", // notes
                BigDecimal.valueOf(100.00), // totalAmount
                mockOrderItems() // items
        );
    }

    private static List<OrderItemDTO> mockOrderItems() {
        return List.of(
                new OrderItemDTO(1L
                        , 12345L
                        , "Pizza Margherita"
                        , BigDecimal.TWO
                        , BigDecimal.valueOf(25.00)
                        , BigDecimal.valueOf(50.00)));
    }
}
