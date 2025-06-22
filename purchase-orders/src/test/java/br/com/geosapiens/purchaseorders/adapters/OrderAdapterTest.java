package br.com.geosapiens.purchaseorders.adapters;

import br.com.geosapiens.purchaseorders.dtos.ResponseOrderDTO;
import br.com.geosapiens.purchaseorders.dtos.ResponseOrderStatusDTO;
import br.com.geosapiens.purchaseorders.dtos.SubmitOrderDTO;
import br.com.geosapiens.purchaseorders.entities.Order;
import br.com.geosapiens.purchaseorders.utils.OrderMockUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderAdapterTest {

    @Test
    void shouldMapDtoToEntity() {
        SubmitOrderDTO dto = OrderMockUtils.mockNewOrder();

        Order order = OrderAdapter.map(dto);

        assertEquals(dto.customerId(), order.getCustomerId());
        assertEquals(dto.restaurantId(), order.getRestaurantId());
        assertEquals(dto.shippingAddress(), order.getShippingAddress());
        assertEquals(dto.notes(), order.getNotes());
        assertEquals(dto.items().size(), order.getItems().size());
    }

    @Test
    void shouldConvertEntityToDto() {
        SubmitOrderDTO dto = OrderMockUtils.mockNewOrder();
        Order order = OrderMockUtils.mockNewOrderSaved(dto);

        ResponseOrderDTO response = OrderAdapter.fromEntity(order);

        assertEquals(order.getId(), response.getOrderId());
        assertEquals(order.getStatus(), response.getOrderStatus());
        assertEquals(order.getItems().size(), response.getItems().size());
        assertEquals(order.getTotalAmount(), response.getTotalAmount());
    }

    @Test
    void shouldReturnStatusDtoFromEntity() {
        SubmitOrderDTO dto = OrderMockUtils.mockNewOrder();
        Order order = OrderMockUtils.mockNewOrderSaved(dto);

        ResponseOrderStatusDTO statusDTO = OrderAdapter.statusFromEntity(order);

        assertEquals(order.getId(), statusDTO.getOrderId());
        assertEquals(order.getStatus(), statusDTO.getOrderStatus());
    }
}
