package br.com.geosapiens.purchaseorders.entities;

import br.com.geosapiens.purchaseorders.enums.EOrderStatus;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tb_order")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Order {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    @Column(name = "id", precision = 19)
    private Long id;

    @Column(name = "customer_id", updatable = false, nullable = false)
    private UUID customerId;

    @Column(name = "restaurant_id", nullable = false, precision = 19)
    private UUID restaurantId;

    @Column(name = "created_date", nullable = false)
    private LocalDateTime createdDate;

    @Column(name = "deliveried_date")
    private LocalDateTime deliveriedDate;

    @Column(name = "status", nullable = false, length = 20)
    @Builder.Default
    private EOrderStatus status = EOrderStatus.PENDING;

    @Column(name = "payment_method", nullable = false, length = 20)
    private String paymentMethod;

    @Column(name = "shipping_address", nullable = false)
    private String shippingAddress;

    @Column(name = "notes")
    private String notes;

    @Column(name = "total_amount", nullable = false, precision = 19, scale = 2)
    @Builder.Default
    private BigDecimal totalAmount = BigDecimal.ZERO;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<OrderItem> items = new ArrayList<>();

    public void addItem(OrderItem item) {
        this.totalAmount = this.totalAmount.add(item.getTotalPrice());
        item.setOrder(this);
        this.items.add(item);
    }

    @PrePersist
    private void prePersist() {
        this.createdDate = LocalDateTime.now();
    }

}
