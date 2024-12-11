package com.MICROSERVICES.PRODUCTS.Service;

import com.MICROSERVICES.PRODUCTS.Model.Product;

import java.util.List;

public interface ProductService {
    Product getProductByproductId(String productId);
    Product createProduct(Product product);
    Product updateProduct(String id, Product product);
    Product getProductById(String id);
    List<Product> getAllProducts();
    List<Product> getProductsByCategory(String category);
    List<Product> getProductsByPriceRange(double minPrice, double maxPrice);
    void deleteProduct(String id);
}