// 代码生成时间: 2025-09-19 16:36:28
package com.inventory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@SpringBootApplication
public class InventoryManagementSystem {

    public static void main(String[] args) {
        SpringApplication.run(InventoryManagementSystem.class, args);
    }

    // Exception handler
    @Bean
    public ResponseEntityExceptionHandler responseEntityExceptionHandler() {
        return new CustomExceptionHandler();
    }

    // WebMvcConfigurer Bean to handle static resources
    @Bean
    public WebMvcConfigurer webMvcConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addResourceHandlers(org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry registry) {
                registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
            }
        };
    }
}

// Custom Exception Handler
class CustomExceptionHandler extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleExceptionInternal(RuntimeException ex, Object controller,
                                                         Exception ex2, WebRequest request) {
        String body = "An error occurred: " + ex.getMessage();
        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

// Inventory Item Entity
class InventoryItem {
    private Long id;
    private String name;
    private int quantity;
    // Getters, Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
}

// Inventory Item Repository Interface
import org.springframework.data.repository.CrudRepository;
import java.util.List;

interface InventoryItemRepository extends CrudRepository<InventoryItem, Long> {
    List<InventoryItem> findByName(String name);
}

// Inventory Item Controller
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/inventory")
class InventoryItemController {

    @Autowired
    private InventoryItemRepository repository;

    @GetMapping("/items")
    public List<InventoryItem> getAllItems() {
        return repository.findAll();
    }

    @PostMapping("/item")
    public InventoryItem createItem(@RequestBody InventoryItem item) {
        return repository.save(item);
    }

    @PutMapping("/item/{id}")
    public ResponseEntity<InventoryItem> updateItem(@PathVariable Long id, @RequestBody InventoryItem itemDetails) {
        InventoryItem item = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item not found"));
        item.setName(itemDetails.getName());
        item.setQuantity(itemDetails.getQuantity());
        return ResponseEntity.ok(repository.save(item));
    }

    @DeleteMapping("/item/{id}")
    public void deleteItem(@PathVariable Long id) {
        repository.deleteById(id);
    }
}