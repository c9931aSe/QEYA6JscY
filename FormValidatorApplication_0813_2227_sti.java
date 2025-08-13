// 代码生成时间: 2025-08-13 22:27:26
@SpringBootApplication
@EnableWebMvc
@RestController
public class FormValidatorApplication {

    @GetMapping("/validate")
    public ResponseEntity<?> validateForm(@RequestParam String input) {
        try {
            if (input == null || input.trim().isEmpty()) {
                throw new IllegalArgumentException("Input cannot be null or empty");
            }
            // 这里可以添加其他验证逻辑
            return ResponseEntity.ok("Validation successful");
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    // 异常处理器
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleValidationExceptions(IllegalArgumentException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    public static void main(String[] args) {
        SpringApplication.run(FormValidatorApplication.class, args);
    }
}