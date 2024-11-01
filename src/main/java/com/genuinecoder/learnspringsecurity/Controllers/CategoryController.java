package com.genuinecoder.learnspringsecurity.Controllers;

import com.genuinecoder.learnspringsecurity.model.Category;
import com.genuinecoder.learnspringsecurity.Repositories.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin/category")
public class CategoryController {

    @Autowired
    private CategoryRepo categoryRepo;

    // GET request to display the category page at /admin/category
    @GetMapping
    public String viewCategoryPage(Model model) {
        List<Category> categories = categoryRepo.findAll();
        model.addAttribute("categories", categories);
        return "ManageCategory"; // Ensure this matches your template name
    }

    // GET request to fetch all categories in JSON format for the frontend
    @GetMapping("/all")
    @ResponseBody // This annotation is used to return JSON directly
    public ResponseEntity<List<Category>> fetchAllCategories() {
        List<Category> categories = categoryRepo.findAll();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    // POST request to save a new category
    @PostMapping("/save")
    public ResponseEntity<String> saveCategory(@RequestParam String name) {
        Category category = new Category();
        category.setName(name);
        categoryRepo.save(category);
        return new ResponseEntity<>("Category saved successfully", HttpStatus.OK);
    }

    // POST request to update an existing category by ID
    @PostMapping("/update")
    public ResponseEntity<String> updateCategory(@RequestParam Long id, @RequestParam String name) {
        Optional<Category> categoryOptional = categoryRepo.findById(id);
        if (categoryOptional.isPresent()) {
            Category category = categoryOptional.get();
            category.setName(name);
            categoryRepo.save(category);
            return new ResponseEntity<>("Category updated successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Category not found", HttpStatus.NOT_FOUND);
        }
    }
}
