// 代码生成时间: 2025-08-19 05:05:01
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.Random;
import java.lang.Exception;

@SpringBootApplication
@RestController
public class RandomNumberGeneratorApplication {

    private final Random random = new Random();

    public static void main(String[] args) {
        SpringApplication.run(RandomNumberGeneratorApplication.class, args);
    }

    @GetMapping("/random")
    public int generateRandomNumber(@RequestParam(required = false, defaultValue = "100") int max) {
        return random.nextInt(max);
    }

    @ExceptionHandler(Exception.class)
    public String handleException(Exception e) {
        e.printStackTrace();
        return "Error occurred: " + e.getMessage();
    }
}
