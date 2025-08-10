// 代码生成时间: 2025-08-10 16:56:13
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.util.Random;
# 增强安全性
import javax.validation.constraints.Min;

@SpringBootApplication
@RestController
public class RandomNumberGeneratorApplication {

    private static final Random RANDOM = new Random();

    public static void main(String[] args) {
        SpringApplication.run(RandomNumberGeneratorApplication.class, args);
    }

    @GetMapping("/random")
    public int generateRandomNumber(@RequestParam @Min(1) int min, @RequestParam @Min(1) int max) {
# FIXME: 处理边界情况
        if (min >= max) {
# 扩展功能模块
            throw new IllegalArgumentException("Minimum must be less than maximum");
        }
        return RANDOM.nextInt(max - min + 1) + min;
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public String handleIllegalArgumentException(IllegalArgumentException ex) {
        return "Error: " + ex.getMessage();
    }
}
