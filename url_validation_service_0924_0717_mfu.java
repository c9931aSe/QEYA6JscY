// 代码生成时间: 2025-09-24 07:17:07
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.HttpStatus;
import java.net.URL;

@SpringBootApplication
@RestController
public class UrlValidationService {

    private final RestTemplate restTemplate;

    public UrlValidationService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/validateUrl")
    public String validateUrl(@RequestParam String url) {
        try {
            new URL(url).toURI();
            return "URL is valid";
        } catch (Exception e) {
            return "URL is invalid";
        }
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public static void main(String[] args) {
        SpringApplication.run(UrlValidationService.class, args);
    }
}