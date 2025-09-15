// 代码生成时间: 2025-09-15 15:37:54
package com.example.passwordtool;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.util.Base64;
import java.util.Map;

@SpringBootApplication
@RestController
@RequestMapping("/api/password")
public class PasswordToolApplication {

    public static void main(String[] args) {
        SpringApplication.run(PasswordToolApplication.class, args);
    }

    @PostMapping("/encrypt")
    public ResponseEntity<String> encryptPassword(@RequestBody Map<String, String> payload) {
        String password = payload.get("password");
        try {
            KeyGenerator keyGen = KeyGenerator.getInstance("AES");
            keyGen.init(128);
            SecretKey secretKey = keyGen.generateKey();
            byte[] passwordBytes = password.getBytes("UTF-8");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] encryptedPasswordBytes = cipher.doFinal(passwordBytes);
            String encryptedPassword = Base64.getEncoder().encodeToString(encryptedPasswordBytes);
            return ResponseEntity.ok(Map.of("encryptedPassword", encryptedPassword, "secretKey", DatatypeConverter.printHexBinary(secretKey.getEncoded())));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Encryption failed", e);
        }
    }

    @PostMapping("/decrypt")
    public ResponseEntity<String> decryptPassword(@RequestBody Map<String, String> payload) {
        String encryptedPassword = payload.get("encryptedPassword");
        String secretKeyHex = payload.get("secretKey");
        try {
            SecretKey secretKey = new SecretKeySpec(DatatypeConverter.parseHexBinary(secretKeyHex), "AES");
            byte[] encryptedPasswordBytes = Base64.getDecoder().decode(encryptedPassword);
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] passwordBytes = cipher.doFinal(encryptedPasswordBytes);
            String password = new String(passwordBytes, "UTF-8");
            return ResponseEntity.ok("decryptedPassword", password);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Decryption failed", e);
        }
    }
}
