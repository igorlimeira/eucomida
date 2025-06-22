package br.com.geosapiens.purchaseorders.dtos;

import br.com.geosapiens.purchaseorders.enums.EOrderStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ResponseOrderStatusDTO {
    private Long orderId;
    private EOrderStatus orderStatus;
}
