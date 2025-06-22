package br.com.geosapiens.purchaseorders.repositories;

import br.com.geosapiens.purchaseorders.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
