package com.MICROSERVICES.ORDERS.Controller;

import com.MICROSERVICES.ORDERS.DTO.OrderDTO;
import com.MICROSERVICES.ORDERS.Model.Order;
import com.MICROSERVICES.ORDERS.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("create")
    public ResponseEntity<Order> createOrder(@RequestBody OrderDTO orderDTO) {
        return new ResponseEntity<>(orderService.createOrder(orderDTO), HttpStatus.CREATED);
    }


    @GetMapping("/products")                // starting here as inter service communication
    public ResponseEntity<String> getAllProducts() {
        String allProducts = orderService.fetchAllProducts();
        return new ResponseEntity<>(allProducts, HttpStatus.OK);
    }
    @GetMapping("/products/{productId}")
    public ResponseEntity<String> getProductDetails(@PathVariable String productId) {
        String productDetails = orderService.getProductDetails(productId);
        return new ResponseEntity<>(productDetails, HttpStatus.OK);
    }


    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        return new ResponseEntity<>(orderService.getAllOrders(), HttpStatus.OK);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<Order> getOrderById(@PathVariable String orderId) {
        return new ResponseEntity<>(orderService.getOrderById(orderId), HttpStatus.OK);
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<Order>> getOrdersByCustomerId(@PathVariable String customerId) {
        return new ResponseEntity<>(orderService.getOrdersByCustomerId(customerId), HttpStatus.OK);
    }

    @GetMapping("/amount/{amount}")
    public ResponseEntity<List<Order>> getOrdersByTotalAmount(@PathVariable double amount) {
        return new ResponseEntity<>(orderService.getOrdersByTotalAmount(amount), HttpStatus.OK);
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<String> deleteOrder(@PathVariable String orderId) {
        orderService.deleteOrder(orderId);
        return new ResponseEntity<>("Order deleted successfully.", HttpStatus.OK);
    }
}