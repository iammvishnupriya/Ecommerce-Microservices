package com.MICROSERVICES.ORDERSTATUS.Controller;

import com.MICROSERVICES.ORDERSTATUS.DTO.OrderStatusDTO;
import com.MICROSERVICES.ORDERSTATUS.Model.OrderStatus;
import com.MICROSERVICES.ORDERSTATUS.Service.OrderStatusService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orderstatus")
public class OrderStatusController {

    private final OrderStatusService orderStatusService;

    public OrderStatusController(OrderStatusService orderStatusService) {
        this.orderStatusService = orderStatusService;
    }

    @PostMapping("/create")
    public ResponseEntity<OrderStatus> createOrderStatus(@RequestBody OrderStatusDTO orderStatusDTO) {
        OrderStatus orderStatus = orderStatusService.createOrderStatus(orderStatusDTO);
        return ResponseEntity.status(201).body(orderStatus);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<OrderStatus> updateOrderStatus(
            @PathVariable String id,
            @RequestParam String status,
            @RequestParam(required = false) String remarks) {
        OrderStatus orderStatus = orderStatusService.updateOrderStatus(id, status, remarks);
        return ResponseEntity.ok(orderStatus);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderStatus> getOrderStatusByOrderId(@PathVariable String orderId) {
        OrderStatus orderStatus = orderStatusService.getOrderStatusByOrderId(orderId);
        return ResponseEntity.ok(orderStatus);
    }
}