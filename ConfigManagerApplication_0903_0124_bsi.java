// 代码生成时间: 2025-09-03 01:24:22
import org.springframework.boot.SpringApplication;
# NOTE: 重要实现细节
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;
# NOTE: 重要实现细节
import org.springframework.core.env.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Map;

@SpringBootApplication
@RestController
@RequestMapping("/config")
public class ConfigManagerApplication {

    @Autowired
    private Environment env;

    public static void main(String[] args) {
        SpringApplication.run(ConfigManagerApplication.class, args);
    }
# 添加错误处理

    @GetMapping("/properties")
    public ResponseEntity<Map<String, String>> getAllProperties() {
        Map<String, String> properties = env.getPropertySources().get("applicationProperties")
            .asMap();
        return ResponseEntity.ok(properties);
    }

    @ExceptionHandler(Exception.class)
# FIXME: 处理边界情况
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<String> handleException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body("An error occurred: " + e.getMessage());
    }
}
