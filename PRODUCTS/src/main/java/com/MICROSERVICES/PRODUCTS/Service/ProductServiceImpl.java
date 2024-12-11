package com.MICROSERVICES.PRODUCTS.Service;

import com.MICROSERVICES.PRODUCTS.Model.Product;
import com.MICROSERVICES.PRODUCTS.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product createProduct(Product product) {
        product.setCreatedAt(LocalDateTime.now());
        product.setUpdatedAt(LocalDateTime.now());
        return productRepository.save(product);
    }


    @Override
    public Product updateProduct(String id, Product product) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        existingProduct.setName(product.getName());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setCategory(product.getCategory());
        existingProduct.setStock(product.getStock());
        existingProduct.setUpdatedAt(LocalDateTime.now());
        return productRepository.save(existingProduct);
    }

    @Override
    public Product getProductById(String id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }
    @Override
    public Product getProductByproductId(String productId) {
        return productRepository.findProductByProductId(productId)
                .orElseThrow(() -> new RuntimeException("Product not found with productId: " + productId));
    }

//    public Product getProductByproductId(String productId) {
//        List<Product> products = productRepository.findAll();
//        return products.stream()
//                .filter(product -> product.getProductId().equals(productId))
//                .findFirst()
//                .orElseThrow(() -> new RuntimeException("Product not found with productId: " + productId));
//    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getProductsByCategory(String category) {
        return productRepository.findByCategory(category);
    }

    @Override
    public List<Product> getProductsByPriceRange(double minPrice, double maxPrice) {
        return productRepository.findByPriceBetween(minPrice, maxPrice);
    }

    @Override
    public void deleteProduct(String id) {
        productRepository.deleteById(id);
    }
}