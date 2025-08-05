// 代码生成时间: 2025-08-05 14:42:18
package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.model.User;
import com.example.demo.service.AuthService;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody User loginRequest) {
        try {
            return ResponseEntity.ok(authService.authenticate(loginRequest));
        } catch (UserNotFoundException ex) {
            return ResponseEntity.status(404).body("User not found");
        }
    }
}

package com.example.demo.exception;

public class UserNotFoundException extends Exception {
    public UserNotFoundException(String message) {
        super(message);
    }
}

package com.example.demo.model;

public class User {
    private String username;
    private String password;
    // Getters and setters
}

package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.exception.UserNotFoundException;;

public interface AuthService {
    User authenticate(User loginRequest) throws UserNotFoundException;
}

package com.example.demo.service.impl;

import com.example.demo.model.User;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.service.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Override
    public User authenticate(User loginRequest) throws UserNotFoundException {
        // Here you would add your authentication logic
        // For this example, we will just throw an exception if the user is not found
        throw new UserNotFoundException("User not found");
    }
}