package com.genuinecoder.learnspringsecurity.Controllers;

import com.genuinecoder.learnspringsecurity.Services.ViewOrderService;
import com.genuinecoder.learnspringsecurity.model.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/view-order")
public class ViewOrderController {

    @Autowired
    private ViewOrderService viewOrderService;

    // Show View Order page with tabs for pending and fulfilled orders
    @GetMapping
    public String showViewOrderPage(Model model) {
        List<Orders> pendingOrders = viewOrderService.getOrdersByStatus("Placed");
        List<Orders> fulfilledOrders = viewOrderService.getOrdersByStatus("Fulfilled");
        model.addAttribute("pendingOrders", pendingOrders);
        model.addAttribute("fulfilledOrders", fulfilledOrders);
        return "view-order";
    }

    // Update order status to "Fulfilled"
    @PostMapping("/mark-complete/{orderId}")
    public String markOrderComplete(@PathVariable("orderId") Long orderId) {
        viewOrderService.markOrderComplete(orderId);
        return "redirect:/admin/view-order";
    }
}
