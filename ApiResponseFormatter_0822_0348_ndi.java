// 代码生成时间: 2025-08-22 03:48:11
package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/api")
public class ApiResponseFormatter {

    // API endpoint to demonstrate response formatting
    @GetMapping("/format")
    public ResponseEntity<String> formatResponse() {
        return ResponseEntity.ok("Response formatted successfully");
    }

    // Exception handler to handle exceptions and format the response
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
    }
}