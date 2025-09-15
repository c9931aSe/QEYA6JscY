// 代码生成时间: 2025-09-16 01:14:17
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;

@SpringBootApplication
@RestController
@RequestMapping("/api/memory")
public class MemoryAnalysisApplication {

    private final MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();

    @GetMapping("/usage")
    public MemoryUsageResponse getMemoryUsage() {
        MemoryUsage heapMemoryUsage = memoryMXBean.getHeapMemoryUsage();
        MemoryUsage nonHeapMemoryUsage = memoryMXBean.getNonHeapMemoryUsage();
        return new MemoryUsageResponse(
                heapMemoryUsage.getInit(),
                heapMemoryUsage.getUsed(),
                heapMemoryUsage.getCommitted(),
                heapMemoryUsage.getMax(),
                nonHeapMemoryUsage.getInit(),
                nonHeapMemoryUsage.getUsed(),
                nonHeapMemoryUsage.getCommitted(),
                nonHeapMemoryUsage.getMax()
        );
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<String> handleException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + ex.getMessage());
    }

    public static void main(String[] args) {
        SpringApplication.run(MemoryAnalysisApplication.class, args);
    }

    static class MemoryUsageResponse {
        private final long heapInit;
        private final long heapUsed;
        private final long heapCommitted;
        private final long heapMax;
        private final long nonHeapInit;
        private final long nonHeapUsed;
        private final long nonHeapCommitted;
        private final long nonHeapMax;

        public MemoryUsageResponse(long heapInit, long heapUsed, long heapCommitted, long heapMax,
                                    long nonHeapInit, long nonHeapUsed, long nonHeapCommitted, long nonHeapMax) {
            this.heapInit = heapInit;
            this.heapUsed = heapUsed;
            this.heapCommitted = heapCommitted;
            this.heapMax = heapMax;
            this.nonHeapInit = nonHeapInit;
            this.nonHeapUsed = nonHeapUsed;
            this.nonHeapCommitted = nonHeapCommitted;
            this.nonHeapMax = nonHeapMax;
        }

        // Getters for all fields
        // ...
    }
}