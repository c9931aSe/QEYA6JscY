// 代码生成时间: 2025-08-29 16:16:21
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@RestController
@RequestMapping("/api")
public class StatisticsDataAnalyzerApplication {

    public static void main(String[] args) {
        SpringApplication.run(StatisticsDataAnalyzerApplication.class, args);
    }

    @GetMapping("/analyze")
    public ResponseEntity<Map<String, Integer>> analyzeData(@RequestParam String data) {
        try {
            int sum = calculateSum(data);
            Map<String, Integer> result = new HashMap<>();
            result.put("Total", sum);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(createErrorResponse(e.getMessage()));
        }
    }

    // Helper method to calculate the sum of data
    private int calculateSum(String data) {
        // Assuming data is a comma-separated string of numbers
        String[] numbers = data.split(",");
        int sum = 0;
        for (String number : numbers) {
            sum += Integer.parseInt(number.trim());
        }
        return sum;
    }

    // Helper method to create error response
    private Map<String, String> createErrorResponse(String message) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("error", message);
        return errorResponse;
    }

    // Exception handler for all controller exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleException(Exception ex) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("error", ex.getMessage());
        return ResponseEntity.badRequest().body(errorResponse);
    }
}