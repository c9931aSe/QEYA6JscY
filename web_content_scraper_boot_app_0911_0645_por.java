// 代码生成时间: 2025-09-11 06:45:59
// Spring Boot Application for Web Content Scraping
// Includes REST API, uses Spring Boot annotations, and adds exception handling.

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestClientException;
import org.springframework.http.HttpMethod;
import java.net.HttpURLConnection;

@SpringBootApplication
public class WebContentScraperBootApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(WebContentScraperBootApplication.class, args);
    }
    
    @RestController
    @RequestMapping("/api")
    class WebContentScraperController {
        
        private final RestTemplate restTemplate;
        
        public WebContentScraperController() {
            HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
            restTemplate = new RestTemplate(requestFactory);
        }
        
        @GetMapping("/fetch")
        public ResponseEntity<String> fetchWebContent(@RequestParam String url) {
            try {
                URL website = new URL(url);
                StringBuilder content = new StringBuilder();

                HttpURLConnection conn = (HttpURLConnection) website.openConnection();
                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String line;

                while ((line = reader.readLine()) != null) {
                    content.append(line).append("
");
                }

                reader.close();
                return ResponseEntity.ok(content.toString());
            } catch (IOException | RestClientException e) {
                return ResponseEntity.internalServerError().body("Failed to fetch the content: " + e.getMessage());
            }
        }
        
        @ExceptionHandler(Exception.class)
        public ResponseEntity<String> handleException(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }
}
