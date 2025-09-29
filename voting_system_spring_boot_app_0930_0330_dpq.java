// 代码生成时间: 2025-09-30 03:30:21
package com.example.votingsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@SpringBootApplication
public class VotingSystemSpringBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(VotingSystemSpringBootApplication.class, args);
    }

    @RestController
    public class VotingController {

        private final ConcurrentHashMap<String, AtomicInteger> votes = new ConcurrentHashMap<>();

        @GetMapping("/vote")
        public ResponseEntity<Object> getVoteResults() {
            return ResponseEntity.ok(votes);
        }

        @PostMapping("/vote")
        public ResponseEntity<Object> castVote(@RequestParam String candidate) {
            if (votes.containsKey(candidate)) {
                votes.get(candidate).incrementAndGet();
            } else {
                votes.put(candidate, new AtomicInteger(1));
            }
            return ResponseEntity.ok(votes);
        }
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(IllegalArgumentException.class)
    public String handleNotFound(Exception e) {
        return e.getMessage();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(Exception.class)
    public String handleBadRequest(Exception e) {
        return e.getMessage();
    }
}

// Note: This code provides a basic structure for a voting system using Spring Boot.
// The VotingController handles GET and POST requests to retrieve and cast votes.
// Exception handling is implemented to return appropriate HTTP status codes for errors.
