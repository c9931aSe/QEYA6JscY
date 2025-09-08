// 代码生成时间: 2025-09-08 16:52:09
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.util.Random;
import java.util.UUID;

@SpringBootApplication
@RestController
public class TestDataGeneratorApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestDataGeneratorApplication.class, args);
    }

    @GetMapping("/generate")
    public ResponseEntity<String> generateTestData(@RequestParam(name = "count", defaultValue = "1") int count) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < count; i++) {
            sb.append("Name:").append(UUID.randomUUID().toString()).append(", Age:").append(random.nextInt(100)).append("
");
        }
        return ResponseEntity.ok(sb.toString());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex) {
        return new ResponseEntity<>("An error occurred: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
