package com.MICROSERVICES.PAYMENTS.Service;

import com.MICROSERVICES.PAYMENTS.DTO.PaymentDTO;
import com.MICROSERVICES.PAYMENTS.Model.Payment;

import java.util.List;

public interface PaymentService {
    Payment createPayment(PaymentDTO paymentDTO);

    Payment getPaymentById(String id);

    List<Payment> getPaymentsByOrderId(String orderId);

    List<Payment> getPaymentsByCustomerId(String customerId);

    List<Payment> getPaymentsByMethod(String method);

    List<Payment> getAllPayments();

    void deletePayment(String id);
}