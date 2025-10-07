// 代码生成时间: 2025-10-08 00:00:23
package com.smartschedule;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.http.ResponseEntity;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.ResponseEntity;

@SpringBootApplication
@RestController
@RequestMapping("/api")
public class SmartScheduleSystem {

    public static void main(String[] args) {
        SpringApplication.run(SmartScheduleSystem.class, args);
    }

    @GetMapping("/schedule")
    public ResponseEntity<String> generateSchedule(@RequestParam(required = false) String className) {
        try {
            if (className == null || className.isEmpty()) {
                throw new IllegalArgumentException("Class name is required.");
            }
            // Logic to generate schedule based on class name
            // For demonstration, return a static response
            return ResponseEntity.ok("Schedule generated for class: " + className);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGlobalException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
    }
}