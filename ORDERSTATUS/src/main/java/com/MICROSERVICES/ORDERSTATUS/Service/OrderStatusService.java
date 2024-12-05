package com.MICROSERVICES.ORDERSTATUS.Service;


import com.MICROSERVICES.ORDERSTATUS.DTO.OrderStatusDTO;
import com.MICROSERVICES.ORDERSTATUS.Model.OrderStatus;

public interface OrderStatusService {
    OrderStatus createOrderStatus(OrderStatusDTO orderStatusDTO);
    OrderStatus updateOrderStatus(String id, String status, String remarks);
    OrderStatus getOrderStatusByOrderId(String orderId);
}
