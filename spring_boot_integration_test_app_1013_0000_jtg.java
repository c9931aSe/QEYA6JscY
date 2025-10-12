// 代码生成时间: 2025-10-13 00:00:14
package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
# 改进用户体验
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ExceptionHandler;
# 添加错误处理
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
# FIXME: 处理边界情况
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;

@SpringBootApplication
# FIXME: 处理边界情况
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
# NOTE: 重要实现细节
    }
# 改进用户体验
}

// 控制器类
@RestController
@RequestMapping("/api")
class ApiController {
# 添加错误处理

    @GetMapping("/test")
    public String testApi() {
        return "API Test Successful";
    }

    @GetMapping("/error")
    public String errorApi() {
        throw new RuntimeException("Something went wrong!");
    }
}
# 改进用户体验

// 全局异常处理器
@ControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> handleRuntimeException(RuntimeException ex) {
        return new ResponseEntity<>("Error: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
