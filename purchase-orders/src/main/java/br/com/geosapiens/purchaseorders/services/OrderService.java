package br.com.geosapiens.purchaseorders.services;

import br.com.geosapiens.purchaseorders.adapters.OrderAdapter;
import br.com.geosapiens.purchaseorders.dtos.ResponseOrderDTO;
import br.com.geosapiens.purchaseorders.dtos.SubmitOrderDTO;
import br.com.geosapiens.purchaseorders.entities.Order;
import br.com.geosapiens.purchaseorders.enums.EOrderError;
import br.com.geosapiens.purchaseorders.exceptions.OrderException;
import br.com.geosapiens.purchaseorders.interfaces.OrderServiceInterface;
import br.com.geosapiens.purchaseorders.repositories.OrderRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class OrderService implements OrderServiceInterface {

    private OrderRepository orderRepository;

    @Override
    public ResponseOrderDTO submitOrder(SubmitOrderDTO submitOrderDTO) {
        try {
            this.orderRepository.deleteAll();
            Order order = this.orderRepository.save(OrderAdapter.map(submitOrderDTO));
            return OrderAdapter.fromEntity(order);
        } catch (DataIntegrityViolationException e) {
            log.error("Data integrity violation while submitting order: ", e);
            throw new OrderException(EOrderError.INVALID_ORDER);
        } catch (Exception e) {
            log.error("Error while submitting order: {}", e.getMessage(), e);
            throw new OrderException(EOrderError.INTERNAL_ERROR);
        }
    }

    @Override
    public ResponseOrderDTO getOrderById(Long orderId) {
        //TODO: Implement the logic to retrieve an order by its ID
        return null;
    }
}
