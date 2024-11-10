package com.genuinecoder.learnspringsecurity.Controllers;

import com.genuinecoder.learnspringsecurity.Services.UserService;
import com.genuinecoder.learnspringsecurity.Services.ViewOrderService;
import com.genuinecoder.learnspringsecurity.model.Orders;
import com.genuinecoder.learnspringsecurity.model.MyUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/user/view-order")
public class ViewUserOrderController {

    @Autowired
    private ViewOrderService viewOrderService;
    @Autowired
    private UserService userService;

    // Show view order of the logged-in user
    @GetMapping
    public String showViewOrderPage(Model model) {

        Long userId = userService.getCurrentUserId();
//        System.out.println("User ID: " + userId);


//        List<Orders> pendingOrders = viewOrderService.getOrdersByUserIdAndStatus(userId, "Pending");
//        List<Orders> fulfilledOrders = viewOrderService.getOrdersByUserIdAndStatus(userId, "Fulfilled");
        List<Orders> pendingOrders = viewOrderService.getOrdersByUserId(userId);
        List<Orders>fulfilledOrders = viewOrderService.getOrdersByUserId(userId);
        model.addAttribute("pendingOrders", pendingOrders);
        model.addAttribute("fulfilledOrders", fulfilledOrders);
        return "view-order-user";
    }
}
