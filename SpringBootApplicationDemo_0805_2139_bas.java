// 代码生成时间: 2025-08-05 21:39:43
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SpringBootApplication
@RestController
@RequestMapping("/api")
public class SpringBootApplicationDemo {

    @GetMapping("/hello")
    public ResponseEntity<String> hello(@RequestParam(name = "name", defaultValue = "World") String name) {
        return ResponseEntity.ok("Hello, " + name + "!");
    }

    // 异常处理类
    @ControllerAdvice
    public class GlobalExceptionHandler {

        @ExceptionHandler(Exception.class)
        @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
        public ResponseEntity<String> handleException(Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringBootApplicationDemo.class, args);
    }
}
