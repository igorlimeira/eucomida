package br.com.geosapiens.purchaseorders.services;

import br.com.geosapiens.purchaseorders.dtos.ResponseOrderDTO;
import br.com.geosapiens.purchaseorders.dtos.SubmitOrderDTO;
import br.com.geosapiens.purchaseorders.entities.Order;
import br.com.geosapiens.purchaseorders.enums.EOrderError;
import br.com.geosapiens.purchaseorders.enums.EOrderStatus;
import br.com.geosapiens.purchaseorders.exceptions.OrderException;
import br.com.geosapiens.purchaseorders.repositories.OrderRepository;
import br.com.geosapiens.purchaseorders.utils.OrderMockUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;

import static br.com.geosapiens.purchaseorders.utils.OrderMockUtils.mockNewOrder;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @InjectMocks
    private OrderService orderService;

    @Mock
    private OrderRepository orderRepository;


    @Test
    void shouldCreateOrderWithPendingStatus() {

        SubmitOrderDTO newMockedOrder = mockNewOrder();

        Order order = OrderMockUtils.mockNewOrderSaved(newMockedOrder);

        when(orderRepository.save(any(Order.class))).thenReturn(order);

        ResponseOrderDTO createdOrder = orderService.submitOrder(newMockedOrder);

        assert createdOrder != null;
        assert createdOrder.getOrderStatus() == EOrderStatus.PENDING;
    }

    @Test
    void shouldThrowOrderExceptionWhenOrderGenericErrorOccurs() {
        SubmitOrderDTO request = mockNewOrder();
        when(orderRepository.save(any(Order.class))).thenThrow(new NullPointerException("Generic error"));

        OrderException thrown = assertThrows(OrderException.class, () -> orderService.submitOrder(request));

        assert thrown.getCode().equals(EOrderError.INTERNAL_ERROR.code());
        assert thrown.getMessage().equals(EOrderError.INTERNAL_ERROR.message());
    }

    @Test
    void shouldThrowOrderExceptionWhenDataIntegrityViolationExceptionOccurs() {
        SubmitOrderDTO request = mockNewOrder();
        when(orderRepository.save(any(Order.class))).thenThrow(new DataIntegrityViolationException("Data integrity violation"));

        OrderException thrown = assertThrows(OrderException.class, () -> orderService.submitOrder(request));

        assert thrown.getCode().equals(EOrderError.INVALID_ORDER.code());
        assert thrown.getMessage().equals(EOrderError.INVALID_ORDER.message());
    }

}