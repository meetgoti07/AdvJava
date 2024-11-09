package com.genuinecoder.learnspringsecurity.Services;

import com.genuinecoder.learnspringsecurity.Repositories.MyUserRepository;
import com.genuinecoder.learnspringsecurity.Repositories.OrderRepository;
import com.genuinecoder.learnspringsecurity.Repositories.ProductRepository;
import com.genuinecoder.learnspringsecurity.model.MyUser;
import com.genuinecoder.learnspringsecurity.model.Orders;
import com.genuinecoder.learnspringsecurity.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private MyUserRepository userRepository; // If you need to fetch user details

    public void placeOrder(List<Long> productIds, MyUser customer) {
        // Fetch selected products
        List<Product> products = productRepository.findAllById(productIds);

        // Create a new order
        Orders order = new Orders();
        order.setOrderItems(products);
        order.setCustomer(customer);
        order.setStatus("Placed");
        order.setCreatedDate(LocalDateTime.now().toString());
        order.setTotal(products.stream().mapToDouble(Product::getSalePrice).sum());

        // Save the order to the database
        orderRepository.save(order);
    }

    // Fetch the customer based on the username (assuming Spring Security)
    public MyUser findCustomerByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
