// 代码生成时间: 2025-09-07 10:18:29
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SpringBootApplication
@RestController
@RequestMapping("/api")
public class ThemeServiceSpringBootApplication {

    private String currentTheme = "light";

    @GetMapping("/theme")
    public String getCurrentTheme() {
        return currentTheme;
    }

    @PostMapping("/theme")
    public ResponseEntity<String> setTheme(@RequestParam String theme) {
        if ("dark".equals(theme) || "light".equals(theme)) {
            this.currentTheme = theme;
            return ResponseEntity.ok("Theme set to: " + theme);
        } else {
            return ResponseEntity.badRequest().body("Invalid theme. Choose 'light' or 'dark'.");
        }
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleInvalidThemeException(Exception e) {
        return "Error: Invalid theme value provided.";
    }

    public static void main(String[] args) {
        SpringApplication.run(ThemeServiceSpringBootApplication.class, args);
    }
}