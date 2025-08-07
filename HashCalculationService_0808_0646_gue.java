// 代码生成时间: 2025-08-08 06:46:58
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/hash")
public class HashCalculationService {

    private static final String HASH_ALGORITHM = "SHA-256";

    @PostMapping("/calculate")
    public ResponseEntity<String> calculateHash(@RequestBody String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance(HASH_ALGORITHM);
            byte[] hash = digest.digest(input.getBytes());
            StringBuilder hexString = new StringBuilder();

            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return ResponseEntity.ok(hexString.toString());
        } catch (NoSuchAlgorithmException e) {
            return ResponseEntity.status(500).body("Hash algorithm not found");
        }
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleException(Exception ex) {
        Map<String, String> errorDetails = new HashMap<>();
        errorDetails.put("errorMessage", ex.getMessage());
        return new ResponseEntity<>(errorDetails, ResponseEntity.status(500).build());
    }
}
