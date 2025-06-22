package br.com.geosapiens.purchaseorders.adapters;

import br.com.geosapiens.purchaseorders.dtos.OrderItemDTO;
import br.com.geosapiens.purchaseorders.dtos.ResponseOrderItemDTO;
import br.com.geosapiens.purchaseorders.entities.Order;
import br.com.geosapiens.purchaseorders.entities.OrderItem;
import br.com.geosapiens.purchaseorders.entities.OrderItemID;
import br.com.geosapiens.utils.UtilBigDecimal;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderItemAdapterTest {

    @Test
    void shouldMapDtoToEntity() {
        Order order = Order.builder().build();
        OrderItemDTO dto = new OrderItemDTO(1L, 10L, "Item", BigDecimal.TWO, BigDecimal.valueOf(5));

        OrderItem item = OrderItemAdapter.map(dto, order);

        assertEquals(order, item.getOrder());
        assertEquals(dto.itemIndex(), item.getId().getItemIdx());
        assertEquals(UtilBigDecimal.multiply(dto.unitPrice(), dto.quantity()), item.getTotalPrice());
    }

    @Test
    void shouldMapEntityListToDtoList() {
        OrderItem item = OrderItem.builder()
                .id(OrderItemID.builder().itemIdx(1L).build())
                .itemId(10L)
                .name("Item")
                .quantity(BigDecimal.ONE)
                .unitPrice(BigDecimal.TEN)
                .totalPrice(BigDecimal.TEN)
                .build();

        List<ResponseOrderItemDTO> dtos = OrderItemAdapter.map(List.of(item));

        assertEquals(1, dtos.size());
        ResponseOrderItemDTO dto = dtos.get(0);
        assertEquals(item.getId().getItemIdx(), dto.getItemIndex());
        assertEquals(item.getTotalPrice(), dto.getTotalPrice());
    }
}
