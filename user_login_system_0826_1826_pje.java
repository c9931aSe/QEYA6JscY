// 代码生成时间: 2025-08-26 18:26:10
package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.service.UserService;
import com.example.demo.model.User;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.exception.InvalidCredentialsException;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User user) {
        try {
            User authenticatedUser = userService.authenticate(user);
            return ResponseEntity.ok(authenticatedUser);
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(404).body("User not found");
        } catch (InvalidCredentialsException e) {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }
}

package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.exception.InvalidCredentialsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User authenticate(User user) throws UserNotFoundException, InvalidCredentialsException {
        User storedUser = userRepository.findByEmail(user.getEmail());
        if(storedUser == null) {
            throw new UserNotFoundException("User not found");
        }
        if(!storedUser.getPassword().equals(user.getPassword())) {
            throw new InvalidCredentialsException("Invalid credentials");
        }
        return storedUser;
    }
}

package com.example.demo.repository;

import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    public User findByEmail(String email);
}

package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User {
    @Id
    private Long id;
    private String email;
    private String password;

    // getters and setters
}

package com.example.demo.exception;

public class UserNotFoundException extends Exception {
    public UserNotFoundException(String message) {
        super(message);
    }
}

package com.example.demo.exception;

public class InvalidCredentialsException extends Exception {
    public InvalidCredentialsException(String message) {
        super(message);
    }
}