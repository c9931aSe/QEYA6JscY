// 代码生成时间: 2025-09-21 06:44:20
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;

@SpringBootApplication
@RestController
@RequestMapping("/api/folder")
public class FolderStructureOrganizer {

    public static void main(String[] args) {
        SpringApplication.run(FolderStructureOrganizer.class, args);
    }

    @GetMapping("/list")
    public ResponseEntity<List<String>> listFiles(@RequestParam String directoryPath) {
        File directory = new File(directoryPath);
        try {
            if (!directory.exists() || !directory.isDirectory()) {
                return ResponseEntity.badRequest().body(null);
            }
            List<String> files = Files.walk(Paths.get(directoryPath))
                    .filter(Files::isRegularFile)
                    .map(path -> path.toString())
                    .collect(Collectors.toList());
            return ResponseEntity.ok(files);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @RestControllerAdvice
    public static class ExceptionHandler {

        @ExceptionHandler(Exception.class)
        public ResponseEntity<String> handleException(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error occurred: " + e.getMessage());
        }
    }
}
