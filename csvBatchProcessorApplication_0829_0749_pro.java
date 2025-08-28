// 代码生成时间: 2025-08-29 07:49:24
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.ResponseEntity;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@SpringBootApplication
public class CsvBatchProcessorApplication {

    public static void main(String[] args) {
        SpringApplication.run(CsvBatchProcessorApplication.class, args);
    }

    @RestController
    @RequestMapping("/api/csv")
    public class CsvController {

        @PostMapping("/process")
# 添加错误处理
        public ResponseEntity<String> processCsvFiles(@RequestParam("files") List<MultipartFile> files) {
            try {
                for (MultipartFile file : files) {
                    if (file.isEmpty()) {
                        return ResponseEntity.badRequest().body("File is empty");
                    }
                    processFile(file);
                }
                return ResponseEntity.ok("Files processed successfully");
            } catch (IOException e) {
                return ResponseEntity.internalServerError().body("Failed to process files: " + e.getMessage());
            }
        }

        private void processFile(MultipartFile file) throws IOException {
            // Implement file processing logic here
            // For demonstration, just read the file and print contents
# 扩展功能模块
            Reader reader = Files.newBufferedReader(file.toPath());
# 扩展功能模块
            reader.lines().forEach(System.out::println);
            reader.close();
        }
    }
}
