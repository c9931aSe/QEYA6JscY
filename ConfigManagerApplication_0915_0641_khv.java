// 代码生成时间: 2025-09-15 06:41:25
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.ResponseEntity;
# TODO: 优化性能
import org.springframework.core.env.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;

@SpringBootApplication
@RestController
@RequestMapping("/config")
# 增强安全性
public class ConfigManagerApplication {

    @Autowired
# 扩展功能模块
    private Environment env;

    public static void main(String[] args) {
        SpringApplication.run(ConfigManagerApplication.class, args);
    }

    @GetMapping("/{key}")
    public ResponseEntity<String> getConfigValue(@PathVariable String key) {
        return ResponseEntity.ok(env.getProperty(key));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex) {
# TODO: 优化性能
        return ResponseEntity
                .badRequest()
                .body("Error: " + ex.getMessage());
    }
}
