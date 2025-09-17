// 代码生成时间: 2025-09-17 16:55:20
package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import java.util.Collections;

@SpringBootApplication
@RestController
@RequestMapping("/api")
public class LoginSystemSpringBootApplication {

    // 启动Spring Boot应用
    public static void main(String[] args) {
        SpringApplication.run(LoginSystemSpringBootApplication.class, args);
    }

    // 用户登录接口
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@Valid @RequestBody LoginRequest loginRequest) {
        try {
            // 模拟登录验证
            if (!authenticateUser(loginRequest.getUsername(), loginRequest.getPassword())) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(Collections.singletonMap("error", "Invalid username or password"));
            }
            return ResponseEntity.ok().body("User logged in successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error");
        }
    }

    // 异常处理器
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationExceptions(MethodArgumentNotValidException ex) {
        return ResponseEntity.badRequest().body(ex.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> fieldError.getDefaultMessage())
                .collect(Collectors.toList()));
    }

    // 模拟用户验证逻辑
    private boolean authenticateUser(String username, String password) {
        // 这里只是一个示例，实际应用中需要更复杂的验证逻辑
        return "user".equals(username) && "password".equals(password);
    }

    // LoginRequest 类，用于接收前端发送的登录请求数据
    public static class LoginRequest {
        private String username;
        private String password;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
}
