// 代码生成时间: 2025-09-10 05:34:58
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.ResponseEntity;
import java.util.Random;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
@RestController
@RequestMapping("/api")
public class RandomNumberGeneratorApplication {

    private static final Random random = new Random();

    public static void main(String[] args) {
        SpringApplication.run(RandomNumberGeneratorApplication.class, args);
    }

    @GetMapping("/generate")
    public ResponseEntity<Integer> generateRandomNumber(@RequestParam(value = "min", defaultValue = "0") int min,
                                                        @RequestParam(value = "max", defaultValue = "100") int max) {
        try {
            if (min >= max) {
                throw new IllegalArgumentException("Invalid range. Max must be greater than min.");
            }
            return ResponseEntity.ok(random.nextInt(max - min + 1) + min);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}
