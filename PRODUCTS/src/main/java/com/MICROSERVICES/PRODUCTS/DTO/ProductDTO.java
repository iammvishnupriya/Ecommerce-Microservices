package com.MICROSERVICES.PRODUCTS.DTO;

import lombok.Data;

@Data
public class ProductDTO {
    private String productId;
    private String id;
    private String name;
    private String description;
    private double price;
    private String category;
    private int stock;
    private String image;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getImage() {
        return image;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public ProductDTO(String productId) {
        this.productId = productId;
    }

    public ProductDTO(String id, String name, String description, double price, String category, int stock, String image) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
        this.stock = stock;
        this.image = image;

    }

    public ProductDTO() {
    }
}