// 代码生成时间: 2025-10-03 03:32:16
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
# 改进用户体验
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@SpringBootApplication
@RestController
@RequestMapping("/api/notifications")
public class NotificationService {

    @GetMapping
# NOTE: 重要实现细节
    public ResponseEntity<String> getNotification(@RequestParam(value = "message", defaultValue = "") String message) {
        if (message.isEmpty()) {
            return ResponseEntity.badRequest().body("Message must not be empty.");
        }
        return ResponseEntity.ok("Notification sent: " + message);
    }

    @ExceptionHandler(Exception.class)
# 添加错误处理
    public ResponseEntity<String> handleException(Exception e) {
        return ResponseEntity.status(500).body("Internal Server Error: " + e.getMessage());
    }
# TODO: 优化性能

    public static void main(String[] args) {
        SpringApplication.run(NotificationService.class, args);
    }
}