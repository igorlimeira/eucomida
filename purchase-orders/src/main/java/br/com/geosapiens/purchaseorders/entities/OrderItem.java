package br.com.geosapiens.purchaseorders.entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "tb_order_item")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class OrderItem {

    @EmbeddedId
    private OrderItemID id;

    @Column(name = "item_id", nullable = false, precision = 19)
    private Long itemId;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "quantity", nullable = false, precision = 10)
    private BigDecimal quantity;

    @Column(name = "unit_price", nullable = false, precision = 19, scale = 2)
    private BigDecimal unitPrice;

    @Column(name = "total_price", nullable = false, precision = 19, scale = 2)
    private BigDecimal totalPrice;

    @JoinColumn(name = "order_id", nullable = false)
    @ManyToOne(optional = false)
    @MapsId("orderId")
    private Order order;

}
