package com.MICROSERVICES.PAYMENTS.Service;

import com.MICROSERVICES.ORDERS.DTO.OrderDTO;
import com.MICROSERVICES.ORDERS.Model.Order;
import com.MICROSERVICES.PAYMENTS.DTO.PaymentDTO;
import com.MICROSERVICES.PAYMENTS.Model.Payment;
import com.MICROSERVICES.PAYMENTS.Repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;


import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;


    @Autowired
    private WebClient.Builder webClientBuilder;

    @Override
    public Payment createPayment(PaymentDTO paymentDTO) {
        // Use WebClient to get order details
        WebClient webClient = webClientBuilder.baseUrl("http://localhost:8080") // Set base URL
                .build(); // Build the WebClient instance

        Order order = webClient.get() // Make GET request
                .uri("/orders/{orderId}", paymentDTO.getOrderId()) // Specify the endpoint and path variable
                .retrieve() // Retrieve the response
                .bodyToMono(Order.class) // Convert the response body to Order class
                .block(); // Block and wait for the response

        // Process payment
        Payment payment = new Payment();
        payment.setOrderId(paymentDTO.getOrderId());
        payment.setAmount(paymentDTO.getAmount());
        payment.setMethod(paymentDTO.getMethod());
        payment.setCreatedAt(LocalDateTime.now());
        payment.setUpdatedAt(LocalDateTime.now());

        return paymentRepository.save(payment);
    }



//    @Override
//    public Payment createPayment(PaymentDTO paymentDTO) {
//        // Fetch order details using Order Service
//        OrderDTO order = webClientBuilder.build()
//                .get()
//                .uri("http://localhost:8080/orders/" + paymentDTO.getOrderId())
//                .retrieve()
//                .bodyToMono(OrderDTO.class)
//                .block();
//
//        if (order == null) {
//            throw new RuntimeException("Order not found for ID: " + paymentDTO.getOrderId());
//        }
//
//        // Create Payment entity
//        Payment payment = new Payment();
//        payment.setPaymentId(UUID.randomUUID().toString());
//        payment.setOrderId(paymentDTO.getOrderId());
//        payment.setAmount(paymentDTO.getAmount());
//        payment.setMethod(paymentDTO.getMethod());
//        payment.setCreatedAt(LocalDateTime.now());
//        payment.setUpdatedAt(LocalDateTime.now());
//
//        return paymentRepository.save(payment);
//    }

//CREATE PAYMENTS
//    @Override
//    public Payment createPayment(PaymentDTO paymentDTO) {
//        Payment payment = new Payment();
//        payment.setPaymentId(UUID.randomUUID().toString());
//        payment.setOrderId(paymentDTO.getOrderId());
//        payment.setCustomerId(paymentDTO.getCustomerId());
//        payment.setProductId(paymentDTO.getProductId());
//        payment.setAmount(paymentDTO.getAmount());
//        payment.setMethod(paymentDTO.getMethod());
//        payment.setCreatedAt(LocalDateTime.now());
//        payment.setUpdatedAt(LocalDateTime.now());
//
//        return paymentRepository.save(payment);
//    }

    @Override
    public Payment getPaymentById(String id) {
        return paymentRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Payment not found with id: " + id));
    }

    @Override
    public List<Payment> getPaymentsByOrderId(String orderId) {
        return paymentRepository.findByOrderId(orderId);
    }



    @Override
    public List<Payment> getPaymentsByCustomerId(String customerId) {
        return paymentRepository.findByCustomerId(customerId);
    }

    @Override
    public List<Payment> getPaymentsByMethod(String method) {
        return paymentRepository.findByMethod(method);
    }

    @Override
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    @Override
    public void deletePayment(String id) {
        Payment payment = paymentRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Payment not found with id: " + id));
        paymentRepository.delete(payment);
    }
}