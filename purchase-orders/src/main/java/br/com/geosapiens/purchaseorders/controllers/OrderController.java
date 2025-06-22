package br.com.geosapiens.purchaseorders.controllers;

import br.com.geosapiens.purchaseorders.dtos.ResponseOrderDTO;
import br.com.geosapiens.purchaseorders.dtos.ResponseOrderStatusDTO;
import br.com.geosapiens.purchaseorders.dtos.SubmitOrderDTO;
import br.com.geosapiens.purchaseorders.interfaces.OrderServiceInterface;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
@AllArgsConstructor
public class OrderController {

    private OrderServiceInterface orderService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseOrderDTO> submitOrder(@RequestBody SubmitOrderDTO submitOrderDTO) {
        return ResponseEntity.ok(orderService.submitOrder(submitOrderDTO));
    }

    @GetMapping(value = "/status/{orderId}", produces =  MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseOrderStatusDTO> getOrderStatus(@PathVariable Long orderId) {
        return ResponseEntity.ok(orderService.getOrderStatusById(orderId));
    }
}
