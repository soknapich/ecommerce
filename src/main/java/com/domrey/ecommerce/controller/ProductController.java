package com.domrey.ecommerce.controller;


import com.domrey.ecommerce.entity.Product;
import com.domrey.ecommerce.repository.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    private static final String UPLOAD_DIR = "uploads/";
    @Autowired
    ProductRepository productRepository;



    @GetMapping("/findAllProduct")
    public ResponseEntity<Map<String, Object>> findAllProduct(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "100") int size,
            @RequestParam(defaultValue = "id") String orderBy,
            @RequestParam(defaultValue = "asc") String sortBy
    ) {
        Map<String, Object> response = new HashMap<>();
        Sort order = Sort.by(orderBy).ascending();
        try {
            if (sortBy.equals("desc")) {
                order = order.descending();
            }
            Pageable pageable = PageRequest.of(page, size, order);
            Page<Product> productList = productRepository.findAll(pageable);
            response.put("status", true);
            response.put("message", "success");
            response.put("data", productList);
        } catch (Error err) {
            response.put("status", false);
            response.put("message", "Data not found");
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("/findProductById")
    public ResponseEntity<Map<String, Object>> findProductById(@RequestParam(name = "id", defaultValue = "0") Long id) {
        Map<String, Object> response = new HashMap<>();
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isPresent()) {
            response.put("status", true);
            response.put("message", "success");
            response.put("data", productRepository.findById(id));
        } else {
            response.put("status", false);
            response.put("message", "Data not found");
        }
        return ResponseEntity.ok(response);
    }

    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> createProduct(@RequestBody Product productObj) {
        Map<String, Object> response = new HashMap<>();
        try {
            response.put("status", true);
            response.put("message", "success");
            response.put("data", productRepository.save(productObj));
        } catch (Error error) {
            response.put("status", false);
            response.put("message", "Data not found");
        }
        return ResponseEntity.ok(response);
    }


    @PatchMapping("/update")
    public ResponseEntity<Map<String, Object>> updateProduct(@RequestParam("id") Long id, @RequestBody Product productObj) {
        Map<String, Object> response = new HashMap<>();
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isPresent()) {
            response.put("status", true);
            response.put("message", "success");
            response.put("data", productRepository.save(productObj));
        } else {
            response.put("status", false);
            response.put("message", "Data not found");
        }
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Map<String, Object>> deleteProduct(@RequestParam Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            Product product = productRepository.findById(id).orElseThrow();
            productRepository.delete(product);
            response.put("status", true);
            response.put("message", "success");
        } catch (Error error) {
            response.put("status", false);
            response.put("message", "Data not found");
        }
        return ResponseEntity.ok(response);
    }


    @PostMapping("/upload")
    public ResponseEntity<Map<String, Object>> upload(@RequestParam("file") MultipartFile file) {
        Map<String, Object> response = new HashMap<>();
        try {
            Path uploadPath = Paths.get(UPLOAD_DIR);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            // Generate a new filename
            String originalFilename = file.getOriginalFilename();
            String extension = "";

            // Allowed extensions
            List<String> allowedExtensions = List.of("png", "jpg", "jpeg");

            // Get file extension
            String fileExtension = originalFilename.substring(originalFilename.lastIndexOf('.') + 1).toLowerCase();
            // Validate extension
            if (!allowedExtensions.contains(fileExtension)) {
                response.put("status", false);
                response.put("message", "Invalid file type: ." + fileExtension);
                return ResponseEntity.ok(response);
            }

            // Get file extension
            int dotIndex = originalFilename.lastIndexOf('.');
            if (dotIndex >= 0) {
                extension = originalFilename.substring(dotIndex);
            }

            // Create new filename, e.g., using UUID
            String newFileName = UUID.randomUUID().toString() + extension;

            Path filePath = uploadPath.resolve(newFileName);
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            //set response back
            response.put("status", true);
            response.put("message", "File uploaded: " + newFileName);
            return ResponseEntity.ok(response);

        } catch (IOException error) {
            response.put("status", false);
            response.put("message", "Failed to upload: " + error.getMessage());
            return ResponseEntity.ok(response);
        }
    }


//    @GetMapping("/chat")
//    public String chat(@RequestParam("message") String message) {
//        return chatClient.call(message);
//    }

}
