package com.genuinecoder.learnspringsecurity.Repositories;

import com.genuinecoder.learnspringsecurity.model.Category;
import com.genuinecoder.learnspringsecurity.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByCategory(Category category);
}
