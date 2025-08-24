// 代码生成时间: 2025-08-24 12:36:40
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.util.Base64;

@SpringBootApplication
@RestController
public class PasswordEncryptionDecryptionService {

    private static final String ALGORITHM = "AES";
    private static final String UTF_8 = "UTF-8";

    // Generate a new AES key
    private static SecretKey generateAESKey() throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(ALGORITHM);
        keyGenerator.init(128); // You can use 192 or 256 bits for a stronger key
        return keyGenerator.generateKey();
    }

    // Encrypt the password using AES algorithm
    @PostMapping("/encrypt")
    public ResponseEntity<String> encrypt(@RequestBody String password) {
        try {
            SecretKey secretKey = generateAESKey();
            byte[] passwordBytes = password.getBytes(UTF_8);
            byte[] keyBytes = secretKey.getEncoded();
            SecretKeySpec secretKeySpec = new SecretKeySpec(keyBytes, ALGORITHM);

            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
            byte[] encryptedBytes = cipher.doFinal(passwordBytes);
            String encryptedPassword = Base64.getEncoder().encodeToString(encryptedBytes);

            return new ResponseEntity<>(encryptedPassword, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Decrypt the password using AES algorithm
    @PostMapping("/decrypt")
    public ResponseEntity<String> decrypt(@RequestBody String encryptedPassword) {
        try {
            SecretKey secretKey = generateAESKey(); // Reuse the same key for decryption
            byte[] encryptedBytes = Base64.getDecoder().decode(encryptedPassword);
            byte[] keyBytes = secretKey.getEncoded();
            SecretKeySpec secretKeySpec = new SecretKeySpec(keyBytes, ALGORITHM);

            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
            byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
            String decryptedPassword = new String(decryptedBytes, UTF_8);

            return new ResponseEntity<>(decryptedPassword, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(PasswordEncryptionDecryptionService.class, args);
    }
}
