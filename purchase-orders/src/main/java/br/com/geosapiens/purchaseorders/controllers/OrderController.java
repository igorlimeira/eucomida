package br.com.geosapiens.purchaseorders.controllers;

import br.com.geosapiens.exceptions.models.ErrorResponse;
import br.com.geosapiens.purchaseorders.dtos.ResponseOrderDTO;
import br.com.geosapiens.purchaseorders.dtos.ResponseOrderStatusDTO;
import br.com.geosapiens.purchaseorders.dtos.SubmitOrderDTO;
import br.com.geosapiens.purchaseorders.interfaces.OrderServiceInterface;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@Tag(name = "Orders", description = "Endpoints for handling customer orders")
@RestController
@RequestMapping("/api/orders")
@AllArgsConstructor
public class OrderController {

    private OrderServiceInterface orderService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
            summary = "Create a new order",
            description = "Creates a new order based on customer, restaurant, items and payment information"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Order created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid order input",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Unexpected server error",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<ResponseOrderDTO> submitOrder(@RequestBody @Valid SubmitOrderDTO submitOrderDTO) {
        return ResponseEntity.ok(orderService.submitOrder(submitOrderDTO));
    }

    @GetMapping(value = "/status/{orderId}", produces =  MediaType.APPLICATION_JSON_VALUE)
    @Operation(
            summary = "Get order status",
            description = "Retrieves the current status of an order by its ID"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Order status retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Order not found",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Unexpected server error",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<ResponseOrderStatusDTO> getOrderStatus(@PathVariable Long orderId) {
        return ResponseEntity.ok(orderService.getOrderStatusById(orderId));
    }
}
