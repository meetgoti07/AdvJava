package com.genuinecoder.learnspringsecurity.Repositories;

import com.genuinecoder.learnspringsecurity.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ViewOrderRepository extends JpaRepository<Orders, Long> {
    List<Orders> findByStatus(String status);
    List<Orders> findByCustomerIdAndStatus(Long customerId, String status);
    List<Orders> findByCustomerId(Long customerId);
}
