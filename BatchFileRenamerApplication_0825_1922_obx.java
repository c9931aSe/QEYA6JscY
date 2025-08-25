// 代码生成时间: 2025-08-25 19:22:16
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@SpringBootApplication
@RestController
@RequestMapping("/api")
public class BatchFileRenamerApplication {

    public static void main(String[] args) {
        SpringApplication.run(BatchFileRenamerApplication.class, args);
    }

    @PostMapping("/rename")
    public ResponseEntity<Map<String, String>> renameFiles(@RequestBody List<Map<String, String>> renameRequests) {
        Map<String, String> result = new HashMap<>();
        for (Map<String, String> renameRequest : renameRequests) {
            String oldName = renameRequest.get("oldName");
            String newName = renameRequest.get("newName");
            try {
                Path oldPath = Paths.get(oldName);
                Path newPath = Paths.get(newName);
                Files.move(oldPath, newPath);
                result.put(oldName, newName);
            } catch (IOException e) {
                result.put(oldName, "Error: " + e.getMessage());
            }
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/health")
    public String healthCheck() {
        return "Service is up and running";
    }
}
