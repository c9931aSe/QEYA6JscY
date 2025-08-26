// 代码生成时间: 2025-08-27 03:39:32
import org.springframework.boot.SpringApplication;
# 添加错误处理
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.nio.charset.StandardCharsets;
import javax.validation.Valid;

@SpringBootApplication
@RestController
@RequestMapping("/api/hash")
public class HashCalculatorApplication {

    public static void main(String[] args) {
# FIXME: 处理边界情况
        SpringApplication.run(HashCalculatorApplication.class, args);
# 优化算法效率
    }

    @PostMapping("/calculate")
    public ResponseEntity<String> calculateHash(@Valid @RequestBody HashRequest request) {
        try {
            String hash = calculateMD5(request.getInput());
            return ResponseEntity.ok(hash);
        } catch (NoSuchAlgorithmException e) {
            return ResponseEntity.badRequest().body("Unsupported hashing algorithm");
# TODO: 优化性能
        }
# NOTE: 重要实现细节
    }

    @GetMapping("/example")
# TODO: 优化性能
    public ResponseEntity<String> example() {
        return ResponseEntity.ok("Use POST request at /api/hash/calculate with a JSON body {"input":"your text"} to calculate the hash");
    }

    private String calculateMD5(String input) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
# 优化算法效率
        byte[] messageDigest = md.digest(input.getBytes(StandardCharsets.UTF_8));
        return bytesToHex(messageDigest);
    }
# 添加错误处理

    private String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
# 增强安全性
        return hexString.toString();
    }
}

class HashRequest {
    private String input;

    public String getInput() {
        return input;
# 添加错误处理
    }

    public void setInput(String input) {
        this.input = input;
    }
}
