// 代码生成时间: 2025-08-25 13:28:52
package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.HttpStatus;

@SpringBootApplication
@RestController
@RequestMapping("/api")
public class RestfulApiApplication {

    private static final String template = "Hello, %s!";
    private final static String[] users = {
        "Alice",
        "Bob"
    };

    public static void main(String[] args) {
        SpringApplication.run(RestfulApiApplication.class, args);
    }

    // GET request to get a list of users
    @GetMapping("/users")
    public String[] getUsers() {
        return users;
    }

    // GET request to get a user by id
    @GetMapping("/users/{id}")
    public ResponseEntity<String> getUserById(@PathVariable String id) {
        for (String user : users) {
            if (user.equalsIgnoreCase(id)) {
                return ResponseEntity.ok(user);
            }
        }
        return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
    }

    // POST request to create a new user
    @PostMapping("/users")
    public String createUser(@RequestBody String newUser) {
        users[users.length - 1] = newUser; // Simple way to add a new user for demonstration
        return String.format(template, newUser);
    }

    // Exception handler
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        return new ResponseEntity<>("An error occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
