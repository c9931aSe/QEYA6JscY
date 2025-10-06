// 代码生成时间: 2025-10-07 03:36:19
package com.example.telemedicine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@SpringBootApplication
@RestController
@RequestMapping("/api")
public class TelemedicineApplication {

    public static void main(String[] args) {
        SpringApplication.run(TelemedicineApplication.class, args);
    }

    // REST API to retrieve health information
    @GetMapping("/health-info")
    public ResponseEntity<String> getHealthInfo() {
        return ResponseEntity.ok("This is a health information from Telemedicine platform.");
    }

    // Exception handling
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<String> handleException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + ex.getMessage());
    }
}

// Additional exception handling can be created for specific exceptions
// and placed in the same or different controller class.