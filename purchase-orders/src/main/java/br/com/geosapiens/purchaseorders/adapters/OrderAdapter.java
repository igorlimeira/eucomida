package br.com.geosapiens.purchaseorders.adapters;

import br.com.geosapiens.purchaseorders.dtos.ResponseOrderDTO;
import br.com.geosapiens.purchaseorders.dtos.ResponseOrderStatusDTO;
import br.com.geosapiens.purchaseorders.dtos.SubmitOrderDTO;
import br.com.geosapiens.purchaseorders.entities.Order;

public class OrderAdapter {

    public static Order map(
            SubmitOrderDTO submitOrderDTO
    ) {
        Order order = new Order();
        order.setCustomerId(submitOrderDTO.customerId());
        order.setRestaurantId(submitOrderDTO.restaurantId());
        order.setPaymentMethod(submitOrderDTO.paymentMethod());
        order.setShippingAddress(submitOrderDTO.shippingAddress());
        order.setNotes(submitOrderDTO.notes());
        order.setTotalAmount(submitOrderDTO.totalAmount());
        submitOrderDTO.items().forEach(item -> order.addItem(OrderItemAdapter.map(item, order)));

        return order;
    }

    public static ResponseOrderDTO fromEntity(Order savedOrder) {
        return ResponseOrderDTO.builder()
                .customerId(savedOrder.getCustomerId())
                .restaurantId(savedOrder.getRestaurantId())
                .orderId(savedOrder.getId())
                .createdAt(savedOrder.getCreatedDate())
                .deliveredAt(savedOrder.getDeliveriedDate())
                .paymentMethod(savedOrder.getPaymentMethod())
                .shippingAddress(savedOrder.getShippingAddress())
                .notes(savedOrder.getNotes())
                .totalAmount(savedOrder.getTotalAmount())
                .orderStatus(savedOrder.getStatus())
                .items(OrderItemAdapter.map(savedOrder.getItems()))
                .build();
    }

    public static ResponseOrderStatusDTO statusFromEntity(Order savedOrder) {
        return ResponseOrderStatusDTO.builder()
                .orderId(savedOrder.getId())
                .orderStatus(savedOrder.getStatus())
                .build();
    }
}
