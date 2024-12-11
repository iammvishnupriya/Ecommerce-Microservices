package com.MICROSERVICES.CUSTOMERS.CUSTOMERS.Service;

import com.MICROSERVICES.CUSTOMERS.CUSTOMERS.Model.Customer;

import java.util.List;

public interface CustomerService {
    Customer createCustomer(Customer customer);
    Customer updateCustomer(String customerId, Customer customer);
    Customer getCustomerByCustomerId(String customerId);
    List<Customer> getAllCustomers();
    void deleteCustomer(String customerId);
}