// 代码生成时间: 2025-10-13 18:28:37
package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.HttpStatus;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@RestController
@RequestMapping("/api/cases")
public class TestCaseManagementService {

    private final List<TestCase> testCases = new ArrayList<>();

    @PostMapping
    public ResponseEntity<TestCase> createTestCase(@Valid @RequestBody TestCase testCase) {
        testCases.add(testCase);
        return ResponseEntity.ok(testCase);
    }

    @GetMapping
    public ResponseEntity<List<TestCase>> getAllTestCases() {
        return ResponseEntity.ok(testCases);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Exception: " + e.getMessage());
    }

    // TestCase POJO
    public static class TestCase {
        private Long id;
        private String description;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getDescription() {
            return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }
    }

    public static void main(String[] args) {
        SpringApplication.run(TestCaseManagementService.class, args);
    }
}