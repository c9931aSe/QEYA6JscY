// 代码生成时间: 2025-10-04 19:06:44
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

@SpringBootApplication
@EnableScheduling
public class TaskSchedulerApp {

    public static void main(String[] args) {
        SpringApplication.run(TaskSchedulerApp.class, args);
    }

    @RestController
    class TaskController {

        @Scheduled(fixedRate = 5000)
        public void executeTask() {
            // 模拟任务执行
            System.out.println("Task executed at: " + System.currentTimeMillis() / 1000);
        }
# 增强安全性

        @GetMapping("/trigger")
        public ResponseEntity<String> triggerTask() {
            executeTask();
            return ResponseEntity.ok("Task triggered");
        }
# 增强安全性
    }
# 扩展功能模块

    @RestController
    class ExceptionController {
        @ExceptionHandler(Exception.class)
        public ResponseEntity<String> handleException(Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + ex.getMessage());
        }
    }
}