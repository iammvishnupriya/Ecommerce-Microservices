package com.MICROSERVICES.CUSTOMERS.CUSTOMERS.Service;

import com.MICROSERVICES.CUSTOMERS.CUSTOMERS.Model.Customer;

import java.util.List;

public interface CustomerService {
    Customer createCustomer(Customer customer);
    Customer updateCustomer(String id, Customer customer);
    Customer getCustomerById(String id);
    List<Customer> getAllCustomers();
    void deleteCustomer(String id);
}