package com.MICROSERVICES.PRODUCTS.Repository;

import com.MICROSERVICES.PRODUCTS.Model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends MongoRepository<Product, String> {
    List<Product> findByCategory(String category);
    List<Product> findByPriceBetween(double minPrice, double maxPrice);
    Optional<Product> findProductByProductId(String productId);
}