package com.genuinecoder.learnspringsecurity.Repositories;

import com.genuinecoder.learnspringsecurity.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
