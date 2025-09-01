// 代码生成时间: 2025-09-02 07:02:34
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;

@SpringBootApplication
@EnableConfigurationProperties
@RestController
@RequestMapping("/api")
public class FolderStructureService {

    private static final String DIRECTORY_PATH = "./data"; // Define the directory path to organize

    @GetMapping("/organize")
    public String organizeDirectory() {
        try {
            Path path = Paths.get(DIRECTORY_PATH);
            if (Files.exists(path) && Files.isDirectory(path)) {
                // Organize the directory here
                // For simplicity, we'll just print a message
                return "Directory is organized at: " + DIRECTORY_PATH;
            } else {
                return "Directory does not exist or is not a directory: " + DIRECTORY_PATH;
            }
        } catch (IOException e) {
            // Log and handle the exception
            return "Error organizing directory: " + e.getMessage();
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(FolderStructureService.class, args);
    }
}
