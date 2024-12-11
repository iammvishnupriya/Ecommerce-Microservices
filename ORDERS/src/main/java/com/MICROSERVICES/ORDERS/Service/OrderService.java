package com.MICROSERVICES.ORDERS.Service;
import com.MICROSERVICES.ORDERS.DTO.OrderDTO;
import com.MICROSERVICES.ORDERS.Model.Order;
import com.MICROSERVICES.PRODUCTS.DTO.ProductDTO;

import java.util.List;
public interface OrderService {


    String getProductDetails(String productId);
    String fetchAllProducts();

    Order createOrder(OrderDTO orderDTO);

    List<Order> getAllOrders(); // Fetch all orders

    Order getOrderById(String orderId); // Fetch a specific order by ID

    List<Order> getOrdersByCustomerId(String customerId); // Fetch orders by customerId

    List<Order> getOrdersByTotalAmount(double amount); // Fetch orders with totalAmount greater than a value

    void deleteOrder(String orderId); // Delete an order by ID
}