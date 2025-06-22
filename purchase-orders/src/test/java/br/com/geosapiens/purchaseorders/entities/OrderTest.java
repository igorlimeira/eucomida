package br.com.geosapiens.purchaseorders.entities;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    @Test
    void addItemShouldUpdateTotalAmountAndSetOrder() {
        Order order = Order.builder().build();

        OrderItem item = OrderItem.builder()
                .id(OrderItemID.builder().itemIdx(1L).build())
                .itemId(10L)
                .name("Pizza")
                .quantity(BigDecimal.ONE)
                .unitPrice(BigDecimal.TEN)
                .totalPrice(BigDecimal.TEN)
                .build();

        order.addItem(item);

        assertEquals(BigDecimal.TEN, order.getTotalAmount());
        assertEquals(order, item.getOrder());
        assertEquals(1, order.getItems().size());
    }
}
