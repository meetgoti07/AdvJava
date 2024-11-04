package com.genuinecoder.learnspringsecurity.Services;

import com.genuinecoder.learnspringsecurity.Repositories.CategoryRepository;
import com.genuinecoder.learnspringsecurity.Repositories.ProductRepository;
import com.genuinecoder.learnspringsecurity.model.Category;
import com.genuinecoder.learnspringsecurity.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Service
public class ProductService {

    private final String imageDirectory = System.getProperty("user.dir") + "/asset/";

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;


    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    //g
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
    }

    public Product addProduct(Product product, MultipartFile imageFile) throws IOException {
        if (!imageFile.isEmpty()) {
            // Ensure the directory exists
            Path assetDirectoryPath = Paths.get(imageDirectory);
            if (!Files.exists(assetDirectoryPath)) {
                Files.createDirectories(assetDirectoryPath);
            }

            // Generate unique filename and store the file
            String fileName = UUID.randomUUID().toString() + "_" + imageFile.getOriginalFilename();
            Path imagePath = Paths.get(imageDirectory + fileName);
            Files.copy(imageFile.getInputStream(), imagePath);
            product.setImageUrl("/images/" + fileName);
        }
        return productRepository.save(product);
    }

    public Product updateProduct(Long id, Product productDetails, MultipartFile imageFile) throws IOException {
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));

        product.setName(productDetails.getName());
        product.setDescription(productDetails.getDescription());
        product.setOriginalPrice(productDetails.getOriginalPrice());
        product.setSalePrice(productDetails.getSalePrice());
        product.setQuantity(productDetails.getQuantity());
        product.setCategory(productDetails.getCategory());

        if (!imageFile.isEmpty()) {
            String oldImageUrl = product.getImageUrl();
            if (oldImageUrl != null) {
                Path oldImagePath = Paths.get(imageDirectory + oldImageUrl.substring("/images/".length()));
                Files.deleteIfExists(oldImagePath);
            }

            // Ensure the directory exists
            Path assetDirectoryPath = Paths.get(imageDirectory);
            if (!Files.exists(assetDirectoryPath)) {
                Files.createDirectories(assetDirectoryPath);
            }

            String fileName = UUID.randomUUID().toString() + "_" + imageFile.getOriginalFilename();
            Path imagePath = Paths.get(imageDirectory + fileName);
            Files.copy(imageFile.getInputStream(), imagePath);
            product.setImageUrl("/images/" + fileName);
        }

        return productRepository.save(product);
    }

    public void deleteProduct(Long id) throws IOException {
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));

        if (product.getImageUrl() != null) {
            Path imagePath = Paths.get(imageDirectory + product.getImageUrl().substring("/images/".length()));
            Files.deleteIfExists(imagePath);
        }

        productRepository.delete(product);
    }
}

