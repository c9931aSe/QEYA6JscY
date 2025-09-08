// 代码生成时间: 2025-09-09 06:05:39
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
# NOTE: 重要实现细节
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@SpringBootApplication
@RestController
public class ReactiveRestApiWithExceptionHandling {

    public static void main(String[] args) {
        SpringApplication.run(ReactiveRestApiWithExceptionHandling.class, args);
    }

    // 响应式REST API end-point
    @GetMapping("/api/items/{id}")
    public Mono<String> getItemById(@PathVariable String id) {
        return Mono.just("Item with ID: " + id);
# 扩展功能模块
    }
# 增强安全性

    // 响应式路由配置
    public RouterFunction<ServerResponse> route() {
        return route(GET("/api/items/{id}"), request -> 
            getItemById(request.pathVariable("id")).flatMap(item -> Mono.just(ServerResponse.ok().bodyValue(item))));
    }

    // 全局异常处理
    @org.springframework.web.bind.annotation.ControllerAdvice
    public static class GlobalExceptionHandler {
# 改进用户体验
        @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
        public Mono<String> handleAllExceptions(Exception e) {
            return Mono.just(e.getMessage());
        }
    }
}
