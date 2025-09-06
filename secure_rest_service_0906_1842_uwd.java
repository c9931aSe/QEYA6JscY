// 代码生成时间: 2025-09-06 18:42:37
// SecureRestService.java
package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.HttpStatus;
import org.springframework.data.jpa.repository.Query;
import com.example.demo.exception.CustomException;
import com.example.demo.repository.UserRepository;
import com.example.demo.model.User;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@RestController
public class SecureRestService {

    @Autowired
    private UserRepository userRepository;

    @PersistenceContext
    private EntityManager entityManager;

    // 异常处理
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<String> handleCustomException(CustomException ce) {
        return new ResponseEntity<>(ce.getMessage(), HttpStatus.BAD_REQUEST);
    }

    // REST API to prevent SQL injection
    @GetMapping("/users")
    public List<User> getUsers(@RequestParam(required = false) String searchTerm) {
        String query = "SELECT u FROM User u WHERE u.username LIKE :searchTerm";
        if (searchTerm != null && !searchTerm.isEmpty()) {
            return userRepository.findAll((root, query, criteriaBuilder) ->
                    criteriaBuilder.like(root.get("username"), "%" + searchTerm + "%"));
        } else {
            return userRepository.findAll();
        }
    }

    // REST API to add a new user, preventing SQL injection through JPA
    @PostMapping("/users")
    public User createUser(@RequestBody User newUser) {
        return userRepository.save(newUser);
    }

    // Query using JPQL to prevent SQL injection
    @Query("SELECT u FROM User u WHERE u.email = :email")
    User findUserByEmail(@RequestParam("email") String email);

    // Example usage of @Query to prevent SQL injection
    @GetMapping("/users/{email}")
    public User getUserByEmail(@RequestParam("email") String email) {
        return entityManager.createQuery(findUserByEmail, User.class).setParameter("email", email).getSingleResult();
    }
}
