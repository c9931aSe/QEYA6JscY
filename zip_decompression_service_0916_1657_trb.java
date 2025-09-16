// 代码生成时间: 2025-09-16 16:57:13
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

@RestController
@RequestMapping("/api/zip")
public class ZipDecompressionService {

    private static final String UPLOAD_DIR = "./uploads/";
    private static final String DESTINATION_DIR = "./decompressed/";

    @PostMapping("/decompress")
    public ResponseEntity<String> decompressZipFile(@RequestParam("file") MultipartFile file) {
        try {
            if (file == null || file.isEmpty()) {
                throw new RuntimeException("Please upload a file");
            }

            String fileName = UPLOAD_DIR + file.getOriginalFilename();
            file.transferTo(new File(fileName));

            decompressFile(fileName, DESTINATION_DIR);

            return ResponseEntity.ok("File decompressed successfully!");
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private void decompressFile(String fileName, String destinationDir) throws IOException {
        ZipInputStream zipIn = new ZipInputStream(new FileInputStream(fileName));
        ZipEntry entry = zipIn.getNextEntry();
        while (entry != null) {
            String filePath = destinationDir + entry.getName();
            extractFile(zipIn, filePath);
            zipIn.closeEntry();
            entry = zipIn.getNextEntry();
        }
        zipIn.close();
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
}
