// 代码生成时间: 2025-09-12 22:10:42
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class BatchFileRenamerApplication {

    public static void main(String[] args) {
        SpringApplication.run(BatchFileRenamerApplication.class, args);
    }

    @RestController
    @RequestMapping("/api/rename")
    public class RenameController {

        @PostMapping
        public ResponseEntity<List<String>> renameFiles(@RequestBody List<FileRenameRequest> requests) {
            try {
                List<String> results = requests.stream()
                        .map(request -> renameFile(request.getOldName(), request.getNewName()))
                        .collect(Collectors.toList());
                return ResponseEntity.ok(results);
            } catch (Exception e) {
                return ResponseEntity.badRequest().body(null);
            }
        }

        private String renameFile(String oldName, String newName) throws Exception {
            Path oldPath = Paths.get(oldName);
            Path newPath = Paths.get(newName);
            if (Files.exists(newPath)) {
                throw new Exception("File already exists: " + newName);
            }
            Files.move(oldPath, newPath);
            return "Renamed from " + oldName + " to " + newName;
        }
    }

    static class FileRenameRequest {
        private String oldName;
        private String newName;

        public String getOldName() {
            return oldName;
        }

        public void setOldName(String oldName) {
            this.oldName = oldName;
        }

        public String getNewName() {
            return newName;
        }

        public void setNewName(String newName) {
            this.newName = newName;
        }
    }
}
