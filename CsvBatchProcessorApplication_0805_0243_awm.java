// 代码生成时间: 2025-08-05 02:43:46
// CsvBatchProcessorApplication.java
package com.example.csvbatchprocessor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import java.io.IOException;
import java.util.Arrays;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;

@RestController
@SpringBootApplication
@RequestMapping("/api")
public class CsvBatchProcessorApplication {

    private static final String TEMP_DIR = "/tmp/csv-processor/";
    private static final String FILE_NOT_FOUND_ERROR_MSG = "File not found.";
    private static final String UPLOAD_ERROR_MSG = "Error occurred while processing the file.";

    public static void main(String[] args) {
        SpringApplication.run(CsvBatchProcessorApplication.class, args);
    }

    @PostMapping("/process")
    public ResponseEntity<String> processCsvFile(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please select a CSV file.");
        }
        try {
            String fileName = file.getOriginalFilename();
            Path filePath = Paths.get(TEMP_DIR + fileName);
            file.transferTo(filePath.toFile());
            processFile(filePath);
            return ResponseEntity.ok("File processed successfully.");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(UPLOAD_ERROR_MSG);
        }
    }

    private void processFile(Path filePath) throws IOException {
        try (CSVParser parser = CSVParser.parse(filePath, CSVFormat.DEFAULT.withFirstRecordAsHeader().withTrim())) {
            parser.getRecords().forEach(record -> {
                // Process each record.
                // For example, save to database or transform data.
                // This is where the actual business logic would be implemented.
            });
        }
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + ex.getMessage());
    }
}
