// 代码生成时间: 2025-08-06 19:04:33
package com.example.networkstatuschecker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import java.net.InetAddress;
import java.net.UnknownHostException;

@SpringBootApplication
@RestController
@RequestMapping("/api")
public class NetworkStatusCheckerApplication {

    public static void main(String[] args) {
        SpringApplication.run(NetworkStatusCheckerApplication.class, args);
    }

    @GetMapping("/check")
    public String checkNetworkStatus() {
        try {
            InetAddress.getByName("8.8.8.8");
            return "Network is available";
        } catch (UnknownHostException e) {
            return "Network is not available";
        }
    }

    // Exception handling
    @ExceptionHandler(UnknownHostException.class)
    public String handleUnknownHostException() {
        return "Error: Network connectivity issue.";
    }
}
