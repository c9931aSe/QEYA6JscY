// 代码生成时间: 2025-09-02 13:14:02
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class InteractiveChartGeneratorApplication {

    public static void main(String[] args) {
        SpringApplication.run(InteractiveChartGeneratorApplication.class, args);
    }

    @RestController
    class ChartController extends ResponseEntityExceptionHandler {

        // Endpoint to generate chart data
        @GetMapping("/chart")
        public List<Double> getChartData(@RequestParam(name = "data") String data) {
            try {
                // Simulate data processing
                String[] values = data.split(",");
                return Arrays.stream(values)
                        .map(Double::valueOf)
                        .toList();
            } catch (Exception e) {
                // Log the exception or throw a custom exception with appropriate message
                throw new RuntimeException("Invalid input data", e);
            }
        }
    }

    // Custom exception handler
    class CustomExceptionHandler extends ResponseEntityExceptionHandler {
        @Override
        protected ResponseEntity<Object> handleRuntimeException(RuntimeException ex, HttpHeaders headers,
                                                           HttpStatus status,
                                                           WebRequest request) {
            return new ResponseEntity<>("{\"error\":\"