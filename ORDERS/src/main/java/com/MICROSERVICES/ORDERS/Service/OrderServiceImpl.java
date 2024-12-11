package com.MICROSERVICES.ORDERS.Service;
import com.MICROSERVICES.ORDERS.DTO.ItemDTO;
import com.MICROSERVICES.ORDERS.DTO.OrderDTO;
import com.MICROSERVICES.ORDERS.Model.Item;
import com.MICROSERVICES.ORDERS.Model.Order;
import com.MICROSERVICES.ORDERS.Repository.OrderRepository;
import com.MICROSERVICES.PRODUCTS.DTO.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import com.MICROSERVICES.CUSTOMERS.CUSTOMERS.DTO.CustomerDTO;
import com.MICROSERVICES.CUSTOMERS.CUSTOMERS.DTO.AddressDTO;







import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private final WebClient.Builder webClientBuilder;

    @Autowired
    public OrderServiceImpl(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }

    @Override
    public String fetchAllProducts() {
        String productServiceUrl = "http://localhost:8080/products/all";

        // Use WebClient to call the Product Service
        return webClientBuilder.build()
                .get()
                .uri(productServiceUrl)
                .retrieve()
                .bodyToMono(String.class)
                .block();  // Synchronous call
    }

    public String getProductDetails(String productId) {
        String productServiceUrl = "http://localhost:8080/products/" + productId; // Call Product Service using WebClient

        return webClientBuilder.build()
                .get()
                .uri(productServiceUrl)
                .retrieve()
                .bodyToMono(String.class)                            // Fetch response as String
                .block();                                           // Synchronous execution
    }



    @Autowired
    private OrderRepository orderRepository;
    @Override
    public Order createOrder(OrderDTO orderDTO) {
        Order order = new Order();
        order.setOrderId(orderDTO.getOrderId());
        order.setCustomerId(orderDTO.getCustomerId());
        order.setCreatedAt(LocalDateTime.now());
        order.setUpdatedAt(LocalDateTime.now());

        // Fetch Customer Data using WebClient
        CustomerDTO customer = webClientBuilder.build()
                .get()
                .uri("http://localhost:8080/customers/" + orderDTO.getCustomerId()) // Assuming Customer Service is running on 8081
                .retrieve()
                .bodyToMono(CustomerDTO.class)
                .block();

        if (customer == null) {
            throw new RuntimeException("Customer not found for ID: " + orderDTO.getCustomerId());
        }

        // Add customer info to the order if necessary
        order.setCustomerName(customer.getCustomerName());
        order.setCustomerEmail(customer.getCustomerEmail());
        order.setCustomerPhone(customer.getCustomerPhone());
        order.setCustomerAddress(customer.getCustomerAddress()); // if you need address info in the order

        List<Item> items = new ArrayList<>();
        for (ItemDTO itemDTO : orderDTO.getItems()) {
            ProductDTO product = webClientBuilder.build()
                    .get()
                    .uri("http://localhost:8080/products/" + itemDTO.getProductId()) // Product service
                    .retrieve()
                    .bodyToMono(ProductDTO.class)
                    .block();

            if (product == null) {
                throw new RuntimeException("Product not found for ID: " + itemDTO.getProductId());
            }

            Item item = new Item();
            item.setProductId(itemDTO.getProductId());
            item.setQuantity(itemDTO.getQuantity());
            item.setPrice(product.getPrice());

            items.add(item);
        }

        order.setItems(items);
        order.setTotalAmount(items.stream().mapToDouble(i -> i.getPrice() * i.getQuantity()).sum());

        return orderRepository.save(order);
    }
    // //TEST CREATE ORDER WITH USING INTER CONNECTION WITH PRODUCT SERVICE[DETAILS]
//    public Order createOrder(OrderDTO orderDTO) {
//        Order order = new Order();
//        order.setOrderId(orderDTO.getOrderId());
//        order.setCustomerId(orderDTO.getCustomerId());
//        order.setCreatedAt(LocalDateTime.now());
//        order.setUpdatedAt(LocalDateTime.now());
//
//        List<Item> items = new ArrayList<>();
//        for (ItemDTO itemDTO : orderDTO.getItems()) {
//            ProductDTO product = webClientBuilder.build()
//                    .get()
//                    .uri("http://localhost:8080/products/" + itemDTO.getProductId())
//                    .retrieve()
//                    .bodyToMono(ProductDTO.class)
//                    .block();
//
//            if (product == null) {
//                throw new RuntimeException("Product not found for ID: " + itemDTO.getProductId());
//            }
//
//            Item item = new Item();
//            item.setProductId(itemDTO.getProductId());
//            item.setQuantity(itemDTO.getQuantity());
//            item.setPrice(product.getPrice());
//
//            items.add(item);
//        }
//
//        order.setItems(items);
//        order.setTotalAmount(items.stream().mapToDouble(i -> i.getPrice() * i.getQuantity()).sum());
//
//        return orderRepository.save(order);
//    }

    //TEST CREATE ORDER WITHOUT USING INTER CONNECTION
//    @Override
//    public Order createOrder(OrderDTO orderDTO) {
//        Order order = new Order();
//        order.setOrderId(UUID.randomUUID().toString());
//        order.setCustomerId(orderDTO.getCustomerId());
//
//        List<Item> items = orderDTO.getItems().stream().map(itemDTO -> {
//            Item item = new Item();
//            item.setProductId(itemDTO.getProductId());
//            item.setQuantity(itemDTO.getQuantity());
//            item.setPrice(itemDTO.getPrice());
//            return item;
//        }).collect(Collectors.toList());
//
//        order.setItems(items);
//        double totalAmount = items.stream().mapToDouble(item -> item.getPrice() * item.getQuantity()).sum();
//        order.setTotalAmount(totalAmount);
//
//        order.setCreatedAt(LocalDateTime.now());
//        order.setUpdatedAt(LocalDateTime.now());
//
//        return orderRepository.save(order);
//    }
    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
    @Override
    public Order getOrderById(String orderId) {
        return orderRepository.findById(orderId).orElseThrow(() ->
                new RuntimeException("Order not found with id: " + orderId));
    }
    @Override
    public List<Order> getOrdersByCustomerId(String customerId) {
        return orderRepository.findByCustomerId(customerId);
    }
    @Override
    public List<Order> getOrdersByTotalAmount(double amount) {
        return orderRepository.findByTotalAmountGreaterThan(amount);
    }
    @Override
    public void deleteOrder(String orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(() ->
                new RuntimeException("Order not found with id: " + orderId));
        orderRepository.delete(order);
    }
}