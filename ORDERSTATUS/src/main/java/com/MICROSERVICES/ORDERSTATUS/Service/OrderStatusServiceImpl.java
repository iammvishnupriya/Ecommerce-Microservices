package com.MICROSERVICES.ORDERSTATUS.Service;

import com.MICROSERVICES.ORDERSTATUS.DTO.OrderStatusDTO;
import com.MICROSERVICES.ORDERSTATUS.Model.OrderStatus;
import com.MICROSERVICES.ORDERSTATUS.Repository.OrderStatusRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class OrderStatusServiceImpl implements OrderStatusService {

    private final OrderStatusRepository orderStatusRepository;

    public OrderStatusServiceImpl(OrderStatusRepository orderStatusRepository) {
        this.orderStatusRepository = orderStatusRepository;
    }

    @Override
    public OrderStatus createOrderStatus(OrderStatusDTO orderStatusDTO) {
        OrderStatus orderStatus = new OrderStatus();
        orderStatus.setOrderId(orderStatusDTO.getOrderId());
        orderStatus.setStatus(orderStatusDTO.getStatus());
        orderStatus.setRemarks(orderStatusDTO.getRemarks());
        orderStatus.setUpdatedAt(LocalDateTime.now());
        return orderStatusRepository.save(orderStatus);
    }

    @Override
    public OrderStatus updateOrderStatus(String id, String status, String remarks) {
        Optional<OrderStatus> optionalOrderStatus = orderStatusRepository.findById(id);
        if (optionalOrderStatus.isPresent()) {
            OrderStatus orderStatus = optionalOrderStatus.get();
            orderStatus.setStatus(status);
            orderStatus.setRemarks(remarks);
            orderStatus.setUpdatedAt(LocalDateTime.now());
            return orderStatusRepository.save(orderStatus);
        }
        throw new RuntimeException("Order Status not found");
    }

    @Override
    public OrderStatus getOrderStatusByOrderId(String orderId) {
        return orderStatusRepository.findAll()
                .stream()
                .filter(status -> status.getOrderId().equals(orderId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Order Status not found for Order ID: " + orderId));
    }
}