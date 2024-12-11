package com.MICROSERVICES.ORDERS.DTO;

import com.MICROSERVICES.CUSTOMERS.CUSTOMERS.DTO.AddressDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class OrderDTO {

    private String customerId;
    private List<ItemDTO> items;
    private String orderId;
    private String customerName;
    private String customerEmail;
    private String customerPhone;
    private AddressDTO customerAddress;
    private double totalAmount;

    // Getters and Setters


    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public List<ItemDTO> getItems() {
        return items;
    }

    public void setItems(List<ItemDTO> items) {
        this.items = items;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

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

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public OrderDTO() {
        this.customerId = customerId;
        this.items = items;
        this.orderId = orderId;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.customerPhone = customerPhone;
        this.customerAddress = customerAddress;
        this.totalAmount = totalAmount;
    }
}