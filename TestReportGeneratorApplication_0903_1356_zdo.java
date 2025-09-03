// 代码生成时间: 2025-09-03 13:56:54
package com.example.testreportgenerator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.Map;

@SpringBootApplication
@RestController
@RequestMapping("/api")
public class TestReportGeneratorApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestReportGeneratorApplication.class, args);
    }

    @GetMapping("/generate")
    public ResponseEntity<String> generateTestReport(@RequestParam Map<String, String> reportData) {
        try {
            // Simulate report generation
            String report = "Test Report Generated";
            return ResponseEntity.ok(report);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while generating report");
        }
    }

    // Exception handling
    @RestControllerAdvice
    public class GlobalExceptionHandler {
        @ExceptionHandler(Exception.class)
        public ResponseEntity<String> handleException(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("There was an error: " + e.getMessage());
        }
    }
}
