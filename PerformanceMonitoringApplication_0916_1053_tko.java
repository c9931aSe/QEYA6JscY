// 代码生成时间: 2025-09-16 10:53:50
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SpringBootApplication
@RestController
@RequestMapping("/api/performance")
public class PerformanceMonitoringApplication {

    public static void main(String[] args) {
        SpringApplication.run(PerformanceMonitoringApplication.class, args);
    }

    @GetMapping("/cpu")
    public ResponseEntity<String> getCpuUsage() {
        // Simulate CPU usage calculation
        String cpuUsage = "50%"; // Placeholder value
        return ResponseEntity.ok(cpuUsage);
    }

    @GetMapping("/memory")
    public ResponseEntity<String> getMemoryUsage() {
        // Simulate memory usage calculation
        String memoryUsage = "75%"; // Placeholder value
        return ResponseEntity.ok(memoryUsage);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex) {
        // Log the exception details
        // Log ex.getMessage();
        return new ResponseEntity<>("Error occurred: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
