package br.com.geosapiens.purchaseorders.services;

import br.com.geosapiens.purchaseorders.dtos.ResponseOrderDTO;
import br.com.geosapiens.purchaseorders.dtos.SubmitOrderDTO;
import br.com.geosapiens.purchaseorders.entities.Order;
import br.com.geosapiens.purchaseorders.enums.EOrderStatus;
import br.com.geosapiens.purchaseorders.repositories.OrderRepository;
import br.com.geosapiens.purchaseorders.utils.OrderMockUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @InjectMocks
    private OrderService orderService;

    @Mock
    private OrderRepository orderRepository;


    @Test
    void shouldCreateOrderWithPendingStatus() {

        SubmitOrderDTO newMockedOrder = OrderMockUtils.mockNewOrder();

        Order order = OrderMockUtils.mockNewOrderSaved(newMockedOrder);

        when(orderRepository.save(Mockito.any(Order.class))).thenReturn(order);

        ResponseOrderDTO createdOrder = orderService.submitOrder(newMockedOrder);

        assert createdOrder != null;
        assert createdOrder.getOrderStatus() == EOrderStatus.PENDING;
    }

}