package com.MICROSERVICES.ORDERS.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemDTO {

    private String productId;
    private int quantity;
    private double price;



}