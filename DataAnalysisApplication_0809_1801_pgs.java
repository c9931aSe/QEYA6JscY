// 代码生成时间: 2025-08-09 18:01:39
package com.example.dataanalysis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@SpringBootApplication
@RestController
public class DataAnalysisApplication {

    public static void main(String[] args) {
        SpringApplication.run(DataAnalysisApplication.class, args);
    }

    @GetMapping("/analyze")
    public String analyzeData(@RequestParam String data) {
        // 这里只是一个示例计算，实际项目中需要根据具体的数据进行分析
        int sum = data.chars().sum();
        return "Sum of data characters: " + sum;
    }

    // 异常处理器
    public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
        @Override
        protected ResponseEntity<Object> handleExceptionInternal(
                Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
            ErrorDetails errorDetails = new ErrorDetails(status, ex.getMessage(), request.getDescription(false));
            return new ResponseEntity<>(errorDetails, headers, status);
        }
    }

    // 错误详情类
    static class ErrorDetails {
        private HttpStatus status;
        private String message;
        private String details;

        public ErrorDetails(HttpStatus status, String message, String details) {
            this.status = status;
            this.message = message;
            this.details = details;
        }

        // Getters and Setters
        public HttpStatus getStatus() { return status; }
        public void setStatus(HttpStatus status) { this.status = status; }
        public String getMessage() { return message; }
        public void setMessage(String message) { this.message = message; }
        public String getDetails() { return details; }
        public void setDetails(String details) { this.details = details; }
    }
}
