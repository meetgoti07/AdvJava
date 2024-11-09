package com.genuinecoder.learnspringsecurity.Controllers;

import com.genuinecoder.learnspringsecurity.Services.OrderService;
import com.genuinecoder.learnspringsecurity.Services.ProductService;
import com.genuinecoder.learnspringsecurity.model.MyUser;
import com.genuinecoder.learnspringsecurity.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/user/products")
public class CreateOrderController {

    @Autowired
    private ProductService productService; // Assuming you have a ProductService

    @Autowired
    private OrderService orderService; // Assuming you have an OrderService

    // Show product list to the customer
    @GetMapping
    public String showProducts(Model model) {
        // Fetch all products from the service
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "product-page"; // This refers to the Thymeleaf template product-page.html
    }

    // Handle order placement
    @PostMapping("/place")
    public String placeOrder(
            @RequestParam("productIds") List<Long> productIds,
            Principal principal,
            RedirectAttributes redirectAttributes) {

        // Assuming you have a method to get the current logged-in customer
        MyUser customer = getCurrentUser(principal);

        // Call the service to place the order
        orderService.placeOrder(productIds, customer);

        // Add a confirmation message
        redirectAttributes.addFlashAttribute("message", "Order placed successfully!");

        return "redirect:/user/home"; // Redirect to product page after placing the order
    }

    // Utility method to fetch current user (assuming you have Spring Security)
    private MyUser getCurrentUser(Principal principal) {
        // Logic to retrieve the current logged-in user based on Principal
        return orderService.findCustomerByUsername(principal.getName());
    }
}
