package com.MICROSERVICES.PRODUCTS.Model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document(collection = "products")
public class Product {
    @Id
    private String id;
    private int productId;
    private String name;
    private String description;
    private double price;
    private String category;
    private int stock;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


}

