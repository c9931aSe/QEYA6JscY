// 代码生成时间: 2025-09-24 12:50:29
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

@SpringBootApplication
public class SortingApplication {

    public static void main(String[] args) {
        SpringApplication.run(SortingApplication.class, args);
    }
}

@RestController
class SortingController {

    @GetMapping("/sort")
    public ResponseEntity<?> sortNumbers(@RequestParam int[] numbers) {
        if (numbers == null || numbers.length == 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please provide an array of numbers.");
        }
        try {
            sortArray(numbers);
            return ResponseEntity.ok(numbers);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }
    
    private void sortArray(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }
}

class SortingException extends RuntimeException {
    public SortingException(String message) {
        super(message);
    }
}