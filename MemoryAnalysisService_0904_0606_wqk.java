// 代码生成时间: 2025-09-04 06:06:32
// MemoryAnalysisService.java
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.ResponseEntity;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;

@SpringBootApplication
@RestController
@RequestMapping("/memory")
public class MemoryAnalysisService {

    private final MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();

    @GetMapping("/usage")
    public ResponseEntity<?> getMemoryUsage() {
        MemoryUsage heapMemoryUsage = memoryMXBean.getHeapMemoryUsage();
        MemoryUsage nonHeapMemoryUsage = memoryMXBean.getNonHeapMemoryUsage();
        return ResponseEntity.ok("
            {
                "heapMemoryUsage": {
                    "init": "" + heapMemoryUsage.getInit() + "",
                    "used": "" + heapMemoryUsage.getUsed() + "",
                    "committed": "" + heapMemoryUsage.getCommitted() + "",
                    "max": "" + heapMemoryUsage.getMax() + ""
                },
                "nonHeapMemoryUsage": {
                    "init": "" + nonHeapMemoryUsage.getInit() + "",
                    "used": "" + nonHeapMemoryUsage.getUsed() + "",
                    "committed": "" + nonHeapMemoryUsage.getCommitted() + "",
                    "max": "" + nonHeapMemoryUsage.getMax() + ""
                }
            }
        ");
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        return ResponseEntity.badRequest().body("Error: "" + e.getMessage() + """);
    }

    public static void main(String[] args) {
        SpringApplication.run(MemoryAnalysisService.class, args);
    }
}
