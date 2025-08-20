// 代码生成时间: 2025-08-21 03:07:17
package com.example.fileextractor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
# TODO: 优化性能
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
# 优化算法效率
import org.springframework.http.ResponseEntity;
# 添加错误处理
import org.springframework.http.HttpStatus;
import java.io.File;
import java.io.IOException;
# 扩展功能模块
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

@SpringBootApplication
@RestController
@RequestMapping("/api")
public class FileExtractorService {

    public static void main(String[] args) {
        SpringApplication.run(FileExtractorService.class, args);
    }
# TODO: 优化性能

    @PostMapping("/extract")
    public ResponseEntity<String> extractFile(@RequestParam("file") MultipartFile file) {
        try {
# NOTE: 重要实现细节
            if (file.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("File is empty");
            }
            String fileName = file.getOriginalFilename();
            String filePath = "./uploads/" + fileName;
            file.transferTo(new File(filePath));

            extractZipFile(filePath);
            return ResponseEntity.ok("File extracted successfully");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error extracting file");
        }
    }

    private void extractZipFile(String filePath) throws IOException {
        try (ZipInputStream zipIn = new ZipInputStream(Files.newInputStream(Paths.get(filePath)))) {
            ZipEntry entry = zipIn.getNextEntry();
            int count;
            byte data[] = new byte[1024];
# 优化算法效率
            while (entry != null) {
                String filePathToSaveFile = "./uploads/" + entry.getName();
                File file = new File(filePathToSaveFile);

                // Create directories for sub directories in zip
                new File(file.getParent()).mkdirs();

                try (FileOutputStream fos = new FileOutputStream(file)) {
                    while ((count = zipIn.read(data)) != -1) {
                        fos.write(data, 0, count);
                    }
                }
                zipIn.closeEntry();
                entry = zipIn.getNextEntry();
            }
        }
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleExceptions(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }
# 添加错误处理
}