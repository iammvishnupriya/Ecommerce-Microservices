package com.MICROSERVICES.CUSTOMERS.CUSTOMERS.Service;

import com.MICROSERVICES.CUSTOMERS.CUSTOMERS.Model.Customer;
import com.MICROSERVICES.CUSTOMERS.CUSTOMERS.Repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Customer createCustomer(Customer customer) {
        customer.setCustomerId("CUST" + System.currentTimeMillis());  // Generate a unique customerId
        customer.setCreatedAt(LocalDateTime.now());
        customer.setUpdatedAt(LocalDateTime.now());
        return customerRepository.save(customer);
    }

    @Override
    public Customer updateCustomer(String customerId, Customer customer) {
        Customer existingCustomer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        existingCustomer.setCustomerName(customer.getCustomerName());
        existingCustomer.setCustomerEmail(customer.getCustomerEmail());
        existingCustomer.setCustomerPhone(customer.getCustomerPhone());
        existingCustomer.setCustomerAddress(customer.getCustomerAddress());
        existingCustomer.setUpdatedAt(LocalDateTime.now());
        return customerRepository.save(existingCustomer);
    }

    @Override
    public Customer getCustomerByCustomerId(String customerId) {
        return customerRepository.findCustomerByCustomerId(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public void deleteCustomer(String customerId) {
        customerRepository.deleteById(customerId);
    }
}