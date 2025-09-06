// 代码生成时间: 2025-09-06 10:28:08
package com.example.automationtestsuite;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.HttpStatus;

@SpringBootApplication
@RestController
public class SpringBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootApplication.class, args);
    }

    // REST API Endpoint
    @GetMapping("/test")
    public String testApi() {
        return "Test API is working";
    }

    // Exception Handler
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex) {
        return new ResponseEntity<>("Error processing request: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
