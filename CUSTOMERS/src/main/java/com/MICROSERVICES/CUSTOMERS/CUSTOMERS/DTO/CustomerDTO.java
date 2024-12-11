package com.MICROSERVICES.CUSTOMERS.CUSTOMERS.DTO;

import lombok.Data;

public class CustomerDTO {
    private String customerName;  // Updated field name
    private String customerEmail; // Updated field name
    private String customerPhone; // Updated field name
    private AddressDTO customerAddress; // Updated field type
    private String customerId;

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public AddressDTO getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(AddressDTO customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public CustomerDTO() {
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.customerPhone = customerPhone;
        this.customerAddress = customerAddress;
        this.customerId = customerId;
    }
}