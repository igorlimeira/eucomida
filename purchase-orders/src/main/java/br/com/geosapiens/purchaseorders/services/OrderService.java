package br.com.geosapiens.purchaseorders.services;

import br.com.geosapiens.purchaseorders.adapters.OrderAdapter;
import br.com.geosapiens.purchaseorders.dtos.ResponseOrderDTO;
import br.com.geosapiens.purchaseorders.dtos.SubmitOrderDTO;
import br.com.geosapiens.purchaseorders.entities.Order;
import br.com.geosapiens.purchaseorders.interfaces.OrderServiceInterface;
import br.com.geosapiens.purchaseorders.repositories.OrderRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class OrderService implements OrderServiceInterface {

    private OrderRepository orderRepository;

    @Override
    public ResponseOrderDTO submitOrder(SubmitOrderDTO submitOrderDTO) {
        try {
            Order order = this.orderRepository.save(OrderAdapter.map(submitOrderDTO));
            return OrderAdapter.fromEntity(order);
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    @Override
    public ResponseOrderDTO getOrderById(Long orderId) {
        //TODO: Implement the logic to retrieve an order by its ID
        return null;
    }
}
