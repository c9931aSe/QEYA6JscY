// 代码生成时间: 2025-08-30 23:20:37
package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;

@SpringBootApplication
public class SpringBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootApplication.class, args);
    }
}

@RestController
class ReactiveController {

    @GetMapping("/api/hello")
    public ServerResponse<String> hello() {
        return ServerResponse.ok().bodyValue("Hello, Reactive Spring Boot!");
    }
}

class ErrorHandling {
    // 这里可以添加异常处理逻辑
}
