// 代码生成时间: 2025-08-15 07:11:12
// ThemeSwitchApplication.java

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class ThemeSwitchApplication {

    public static void main(String[] args) {
        SpringApplication.run(ThemeSwitchApplication.class, args);
    }
}

@RestController
class ThemeController {

    private Map<String, String> themeMap = new HashMap<>();
    private String currentTheme;

    public ThemeController() {
        themeMap.put("dark", "Dark theme activated");
        themeMap.put("light", "Light theme activated");
        currentTheme = "light";
    }

    @GetMapping("/switchTheme")
    public ResponseEntity<String> switchTheme(@RequestParam String theme) {
        if (themeMap.containsKey(theme)) {
            currentTheme = theme;
            return ResponseEntity.ok(themeMap.get(theme));
        } else {
            return ResponseEntity.badRequest().body("Invalid theme");
        }
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleInvalidThemeException(Exception e) {
        return ResponseEntity.badRequest().body("An error occurred: " + e.getMessage());
    }
}
