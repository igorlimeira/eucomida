package br.com.geosapiens.purchaseorders.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EOrderStatus {
    PENDING("Pendente"),
    IN_PROGRESS("Em andamento"),
    DELIVERED("Entregue"),
    CANCELLED("Cancelado");

    private final String description;
}
