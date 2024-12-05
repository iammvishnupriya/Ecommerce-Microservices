package com.MICROSERVICES.PAYMENTS.Repository;

import com.MICROSERVICES.PAYMENTS.Model.Payment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends MongoRepository<Payment, String> {


    List<Payment> findByOrderId(String orderId);

    List<Payment> findByCustomerId(String customerId);

    List<Payment> findByMethod(String method); // Payments by method (e.g., Credit Card)
}