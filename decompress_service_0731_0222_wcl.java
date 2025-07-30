// 代码生成时间: 2025-07-31 02:22:24
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.ResponseEntity;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.List;
import java.util.ArrayList;

@SpringBootApplication
@RestController
public class DecompressService {

    private static final String UPLOAD_DIR = "uploads/"; // 文件上传目录

    @PostMapping("/decompress")
    public ResponseEntity<String> decompressFile(@RequestParam("file") MultipartFile file) {
        try {
            if (file.isEmpty()) {
                return ResponseEntity.badRequest().body("File is empty");
            }
            String originalFilename = file.getOriginalFilename();
            Path destinationPath = Paths.get(UPLOAD_DIR + originalFilename);
            Files.copy(file.getInputStream(), destinationPath);
            return decompress(destinationPath);
        } catch (IOException e) {
            return ResponseEntity.internalServerError().body("Error occurred: " + e.getMessage());
        }
    }

    private ResponseEntity<String> decompress(Path filePath) throws IOException {
        File destDir = new File(filePath.getParent().toFile(), "decompressed");
        if (!destDir.exists()) {
            destDir.mkdir();
        }
        ZipInputStream zipIn = new ZipInputStream(Files.newInputStream(filePath));
        ZipEntry entry = zipIn.getNextEntry();
        List<String> extractedFiles = new ArrayList<>();
        while (entry != null) {
            String filePathToExtract = destDir.getAbsolutePath() + File.separator + entry.getName();
            if (!entry.isDirectory()) {
                extractFile(zipIn, filePathToExtract);
                extractedFiles.add(filePathToExtract);
            }
            zipIn.closeEntry();
            entry = zipIn.getNextEntry();
        }
        zipIn.close();
        return ResponseEntity.ok("Decompressed files: " + String.join(",", extractedFiles));
    }

    private void extractFile(ZipInputStream zipIn, String filePath) throws IOException {
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(filePath));
        byte[] bytesIn = new byte[4096];
        int read = 0;
        while ((read = zipIn.read(bytesIn)) != -1) {
            bos.write(bytesIn, 0, read);
        }
        bos.close();
    }

    public static void main(String[] args) {
        SpringApplication.run(DecompressService.class, args);
    }
}
