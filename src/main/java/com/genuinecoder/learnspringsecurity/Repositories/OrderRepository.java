package com.genuinecoder.learnspringsecurity.Repositories;

import com.genuinecoder.learnspringsecurity.model.MyUser;
import com.genuinecoder.learnspringsecurity.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface OrderRepository extends JpaRepository<Orders, Long> {

    List<Orders> findByCustomer(MyUser customer);

}