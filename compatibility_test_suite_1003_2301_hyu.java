// 代码生成时间: 2025-10-03 23:01:33
package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SpringBootApplication
@RestController
@RequestMapping("/api")
public class CompatibilityTestSuite {

    @GetMapping("/health")
    public String healthCheck() {
        return "Service is up and running";
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex) {
        return new ResponseEntity<>("An error occurred: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public static void main(String[] args) {
        SpringApplication.run(CompatibilityTestSuite.class, args);
    }
}
