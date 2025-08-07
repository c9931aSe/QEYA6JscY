// 代码生成时间: 2025-08-07 10:56:21
package com.example.configmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
# NOTE: 重要实现细节
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Value;
# 改进用户体验
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@SpringBootApplication
# 改进用户体验
@RestController
@RequestMapping("/api")
public class ConfigManagerApplication {
# 添加错误处理

    @Value("{property.value}")
    private String propertyValue;

    public static void main(String[] args) {
        SpringApplication.run(ConfigManagerApplication.class, args);
    }

    @GetMapping("/getConfig")
# TODO: 优化性能
    public ResponseEntity<String> getConfig(@RequestParam String key) {
        return ResponseEntity.ok(propertyValue);
# TODO: 优化性能
    }

    @RestControllerAdvice
    class GlobalExceptionHandler {

        @ExceptionHandler(Exception.class)
        @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
        public ResponseEntity<String> handleGenericException(Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

// application.properties
// property.value=This is a config value