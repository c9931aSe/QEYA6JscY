// 代码生成时间: 2025-10-04 03:11:19
package com.example.nutritionanalysis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.converter.HttpMessageNotReadableException;

@SpringBootApplication
@RestController
@RequestMapping("/api")
public class NutritionAnalysisApplication {

    public static void main(String[] args) {
        SpringApplication.run(NutritionAnalysisApplication.class, args);
    }

    @GetMapping("/analyze")
    public ResponseEntity<String> analyzeNutrition(@RequestParam String food) {
        if (food == null || food.trim().isEmpty()) {
            return ResponseEntity
                    .badRequest()
                    .body("Error: Food item is required.");
        }
        // Here, you would normally call a service to analyze the nutrition of the food item.
        // For demonstration purposes, we are returning a hardcoded response.
        return ResponseEntity.ok("Nutrition analysis for: " + food);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("Error: Could not parse request. Please check your input format.");
    }

    // You can add more exception handlers here if needed.
}
