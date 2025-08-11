// 代码生成时间: 2025-08-11 12:54:01
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

@SpringBootApplication
@RestController
public class BatchImageResizerSpringBootApplication {

    @PostMapping("/resize")
    public ResponseEntity<Object> resizeImages(@RequestParam("files") MultipartFile[] files, @RequestParam("width") int width, @RequestParam("height") int height) {
        try {
            if (files == null || files.length == 0) {
                return ResponseEntity.badRequest().body("No files provided");
            }

            for (MultipartFile file : files) {
                resizeImage(file.getInputStream(), width, height);
            }

            return ResponseEntity.ok("All images have been resized");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error resizing images");
        }
    }

    private void resizeImage(InputStream inputStream, int width, int height) throws IOException {
        BufferedImage originalImage = ImageIO.read(inputStream);
        BufferedImage resizedImage = new BufferedImage(width, height, originalImage.getType());
        resizedImage.getGraphics().drawImage(originalImage, 0, 0, width, height, null);
        resizedImage.getGraphics().dispose();

        OutputStream outputStream = new FileOutputStream("resized_" + new File(inputStream.toString()).getName());
        ImageIO.write(resizedImage, "jpg", outputStream);
        outputStream.close();
    }

    public static void main(String[] args) {
        SpringApplication.run(BatchImageResizerSpringBootApplication.class, args);
    }
}
