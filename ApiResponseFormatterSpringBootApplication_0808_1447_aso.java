// 代码生成时间: 2025-08-08 14:47:25
package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@SpringBootApplication
@RestController
@RequestMapping("/api")
public class ApiResponseFormatterSpringBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiResponseFormatterSpringBootApplication.class, args);
    }

    @GetMapping("/format")
    public ResponseEntity<String> formatResponse(@RequestParam(required = false) String message) {
        if (message == null) {
            message = "Hello, World!";
        }
        return ResponseEntity.ok().body("{"message":"" + message + ""}");
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        return ResponseEntity.badRequest().body("{"error":"An error occurred: " + e.getMessage() + ""}");
    }
}