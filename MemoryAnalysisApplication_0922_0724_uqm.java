// 代码生成时间: 2025-09-22 07:24:14
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;

@SpringBootApplication
@RestController
@RequestMapping("/memory")
public class MemoryAnalysisApplication {

    private static final MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();

    @GetMapping
    public ResponseEntity<MemoryInfo> getMemoryInfo() {
        MemoryInfo memoryInfo = new MemoryInfo();

        MemoryUsage heapMemoryUsage = memoryMXBean.getHeapMemoryUsage();
        memoryInfo.setHeapUsed(heapMemoryUsage.getUsed());
        memoryInfo.setHeapCommitted(heapMemoryUsage.getCommitted());
        memoryInfo.setHeapMax(heapMemoryUsage.getMax());
        memoryInfo.setHeapInit(heapMemoryUsage.getInit());

        MemoryUsage nonHeapMemoryUsage = memoryMXBean.getNonHeapMemoryUsage();
        memoryInfo.setNonHeapUsed(nonHeapMemoryUsage.getUsed());
        memoryInfo.setNonHeapCommitted(nonHeapMemoryUsage.getCommitted());
        memoryInfo.setNonHeapMax(nonHeapMemoryUsage.getMax());
        memoryInfo.setNonHeapInit(nonHeapMemoryUsage.getInit());

        return ResponseEntity.ok(memoryInfo);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        return ResponseEntity.badRequest().body("Error: " + e.getMessage());
    }

    public static void main(String[] args) {
        SpringApplication.run(MemoryAnalysisApplication.class, args);
    }
}

class MemoryInfo {
    private long heapUsed;
    private long heapCommitted;
    private long heapMax;
    private long heapInit;
    private long nonHeapUsed;
    private long nonHeapCommitted;
    private long nonHeapMax;
    private long nonHeapInit;

    // Getters and Setters
    public long getHeapUsed() { return heapUsed; }
    public void setHeapUsed(long heapUsed) { this.heapUsed = heapUsed; }
    public long getHeapCommitted() { return heapCommitted; }
    public void setHeapCommitted(long heapCommitted) { this.heapCommitted = heapCommitted; }
    public long getHeapMax() { return heapMax; }
    public void setHeapMax(long heapMax) { this.heapMax = heapMax; }
    public long getHeapInit() { return heapInit; }
    public void setHeapInit(long heapInit) { this.heapInit = heapInit; }
    public long getNonHeapUsed() { return nonHeapUsed; }
    public void setNonHeapUsed(long nonHeapUsed) { this.nonHeapUsed = nonHeapUsed; }
    public long getNonHeapCommitted() { return nonHeapCommitted; }
    public void setNonHeapCommitted(long nonHeapCommitted) { this.nonHeapCommitted = nonHeapCommitted; }
    public long getNonHeapMax() { return nonHeapMax; }
    public void setNonHeapMax(long nonHeapMax) { this.nonHeapMax = nonHeapMax; }
    public long getNonHeapInit() { return nonHeapInit; }
    public void setNonHeapInit(long nonHeapInit) { this.nonHeapInit = nonHeapInit; }
}
