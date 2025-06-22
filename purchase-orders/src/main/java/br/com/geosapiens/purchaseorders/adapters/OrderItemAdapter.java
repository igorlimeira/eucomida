package br.com.geosapiens.purchaseorders.adapters;

import br.com.geosapiens.purchaseorders.dtos.OrderItemDTO;
import br.com.geosapiens.purchaseorders.dtos.ResponseOrderItemDTO;
import br.com.geosapiens.purchaseorders.entities.OrderItem;
import br.com.geosapiens.purchaseorders.entities.OrderItemID;

import java.util.List;

public class OrderItemAdapter {

    static OrderItem map(OrderItemDTO item) {
        return OrderItem.builder()
                .id(OrderItemID.builder()
                        .itemIdx(item.itemIndex())
                        .build())
                .itemId(item.productId())
                .name(item.productName())
                .quantity(item.quantity())
                .unitPrice(item.unitPrice())
                .totalPrice(item.totalPrice())
                .build();
    }


    public static List<ResponseOrderItemDTO> map(List<OrderItem> items) {
        return items.stream()
                .map(item -> ResponseOrderItemDTO.builder()
                        .itemIndex(item.getId().getItemIdx())
                        .productId(item.getItemId())
                        .productName(item.getName())
                        .quantity(item.getQuantity())
                        .unitPrice(item.getUnitPrice())
                        .totalPrice(item.getTotalPrice())
                        .build())
                .toList();
    }
}
