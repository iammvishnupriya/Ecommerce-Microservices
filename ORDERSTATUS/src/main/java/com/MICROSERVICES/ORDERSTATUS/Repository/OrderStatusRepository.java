package com.MICROSERVICES.ORDERSTATUS.Repository;

import com.MICROSERVICES.ORDERSTATUS.Model.OrderStatus;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderStatusRepository extends MongoRepository<OrderStatus, String> {
}