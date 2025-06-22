package br.com.geosapiens.purchaseorders.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemID {
    @Column(name = "item_idx", nullable = false, precision = 19)
    private Long itemIdx;

    @Column(name = "order_id", nullable = false, precision = 19)
    private Long orderId;
}
