// 代码生成时间: 2025-10-01 23:20:40
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseStatus;

@SpringBootApplication
@RestController
@RequestMapping("/api")
public class ApiTestingToolApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiTestingToolApplication.class, args);
    }

    @GetMapping("/greet/{name}")
    public ResponseEntity<String> greet(@PathVariable String name) {
        return ResponseEntity.ok("Hello, " + name);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<String> handleException(Exception e) {
        return ResponseEntity.status(org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
    }
}
