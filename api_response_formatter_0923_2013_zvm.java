// 代码生成时间: 2025-09-23 20:13:32
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class ApiResponseFormatterApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiResponseFormatterApplication.class, args);
    }
}

@RestController
class ApiResponseController {

    @GetMapping("/format")
    public ResponseEntity<Map<String, Object>> formatResponse(@RequestParam(name = "message") String message) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("data", message);
        return ResponseEntity.ok(response);
    }

    // Add more API endpoints as needed
}

@ControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Map<String, Object>> handleException(Exception ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "error");
        response.put("message", ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    // Add more exception handlers as needed
}
