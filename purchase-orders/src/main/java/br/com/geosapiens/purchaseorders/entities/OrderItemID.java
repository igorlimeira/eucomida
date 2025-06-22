package br.com.geosapiens.purchaseorders.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode(callSuper = false)
public class OrderItemID implements Serializable {
    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "item_idx")
    private Long itemIdx;
}
