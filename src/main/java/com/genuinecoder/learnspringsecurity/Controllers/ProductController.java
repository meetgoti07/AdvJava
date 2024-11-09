package com.genuinecoder.learnspringsecurity.Controllers;

import com.genuinecoder.learnspringsecurity.Services.ProductService;
import com.genuinecoder.learnspringsecurity.model.Product;
import com.genuinecoder.learnspringsecurity.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/admin/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    // List all products
    @GetMapping
    public String listProducts(Model model) {
        List<Product> products = productService.getAllProducts();
        System.out.println(products);
        model.addAttribute("products", products);
        return "product-list";
    }

    // Add a new product with category dropdown
    @GetMapping("/add")
    public String addProductForm(Model model) {
        List<Category> categories = productService.getAllCategories();  // Get categories for dropdown
        model.addAttribute("product", new Product());
        model.addAttribute("categories", categories);
        return "add-product";  // Thymeleaf template to add product
    }

    @PostMapping("/add")
    public String addProduct(@ModelAttribute Product product,
                             @RequestParam("productImage") MultipartFile imageFile) throws IOException {
        productService.addProduct(product, imageFile);
        return "redirect:/admin/products";
    }


    // Edit an existing product
    @GetMapping("/edit/{id}")
    public String editProductForm(@PathVariable("id") Long id, Model model) {
        Product product = productService.getProductById(id);
        List<Category> categories = productService.getAllCategories();  // Get categories for dropdown
        model.addAttribute("product", product);
        model.addAttribute("categories", categories);
        return "edit-product";  // Thymeleaf template to edit product
    }

    @PostMapping("/edit/{id}")
    public String editProduct(@PathVariable("id") Long id,
                              @ModelAttribute Product product,
                              @RequestParam("imageFile") MultipartFile imageFile) throws IOException {
        productService.updateProduct(id, product, imageFile);
        return "redirect:/admin/products";
    }


    // Get product details for edit (REST API endpoint to return JSON data)
    @GetMapping("/edit/api/{id}")
    public ResponseEntity<Product> getProductDetails(@PathVariable("id") Long id) {
        Product product = productService.getProductById(id);
        return ResponseEntity.ok(product);
    }

    // Delete a product
    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") Long id) throws IOException {
        productService.deleteProduct(id);
        return "redirect:/admin/products";
    }
}
