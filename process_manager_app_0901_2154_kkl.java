// 代码生成时间: 2025-09-01 21:54:42
package com.example.processmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@RestController
@RequestMapping("/process")
public class ProcessManagerApp {

    public static void main(String[] args) {
        SpringApplication.run(ProcessManagerApp.class, args);
    }

    @GetMapping("/list")
    public List<String> listProcesses() {
        List<String> processes = new ArrayList<>();
        try {
            // Simulating process listing
            processes.add("Process 1");
            processes.add("Process 2");
            processes.add("Process 3");
        } catch (Exception e) {
            // Log the error here
        }
        return processes;
    }

    @ExceptionHandler(Exception.class)
    public String handleException(Exception e) {
        // Log the exception
        return "Error: " + e.getMessage();
    }
}