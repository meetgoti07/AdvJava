package com.genuinecoder.learnspringsecurity.Services;

import com.genuinecoder.learnspringsecurity.Repositories.ViewOrderRepository;
import com.genuinecoder.learnspringsecurity.model.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class ViewOrderService {

    @Autowired
    private ViewOrderRepository viewOrderRepository;

    public List<Orders> getOrdersByStatus(String status) {
        return viewOrderRepository.findByStatus(status);
    }

    @Transactional
    public void markOrderComplete(Long orderId) {
        Orders order = viewOrderRepository.findById(orderId).orElseThrow();
        order.setStatus("Fulfilled");
        order.setClosedDate(LocalDate.now().toString());
        viewOrderRepository.save(order);
    }
}
