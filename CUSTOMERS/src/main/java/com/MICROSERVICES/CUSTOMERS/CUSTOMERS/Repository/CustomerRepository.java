package com.MICROSERVICES.CUSTOMERS.CUSTOMERS.Repository;

import com.MICROSERVICES.CUSTOMERS.CUSTOMERS.Model.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CustomerRepository extends MongoRepository<Customer, String> {
    Optional<Customer> findByCustomerEmail(String customerEmail);
    Optional<Customer> findCustomerByCustomerId(String customerId);
}