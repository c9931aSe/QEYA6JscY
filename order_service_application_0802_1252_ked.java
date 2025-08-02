// 代码生成时间: 2025-08-02 12:52:18
package com.example.orderapplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.HttpStatus;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@SpringBootApplication
@RestController
@RequestMapping("/api/orders")
public class OrderServiceApplication {
    
    private final List<Order> orders = new ArrayList<>();
    private static int orderId = 1;
    
    public static void main(String[] args) {
        SpringApplication.run(OrderServiceApplication.class, args);
    }
    
    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        order.setId(orderId++);
        orders.add(order);
        return ResponseEntity.ok(order);
    }
    
    @GetMapping(