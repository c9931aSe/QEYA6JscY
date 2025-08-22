// 代码生成时间: 2025-08-22 21:27:54
package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.HttpStatus;

@SpringBootApplication
@RestController
@RequestMapping("/api")
public class SpringBootApplication {

    @GetMapping("/test")
    public ResponseEntity<String> testApi(@RequestParam(name = "name", defaultValue = "World") String name) {
        return ResponseEntity.ok("Hello, " + name);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleExceptions(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + ex.getMessage());
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringBootApplication.class, args);
    }
}
