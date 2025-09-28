// 代码生成时间: 2025-09-29 00:00:54
package com.agriculture.iot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@RestController
@RequestMapping("/api")
public class AgricultureIoTService {

    public static void main(String[] args) {
        SpringApplication.run(AgricultureIoTService.class, args);
    }

    // REST API to get sensor data
    @GetMapping("/sensors")
    public ResponseEntity<Map<String, Object>> getSensorData(@RequestParam String sensorId) {
        Map<String, Object> data = new HashMap<>();
        // Simulate sensor data retrieval
        data.put("temperature", 22.5);
        data.put("humidity", 60);
        return ResponseEntity.ok(data);
    }

    // REST API to add new sensor data
    @PostMapping("/sensors")
    public ResponseEntity<String> addSensorData(@RequestBody SensorData sensorData) {
        // Simulate adding sensor data to the database
        // Here we just return a success message
        return ResponseEntity.ok("Sensor data added successfully");
    }

    // Exception handling
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleException(Exception ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", ex.getMessage());
        return ResponseEntity.badRequest().body(error);
    }

    // SensorData class to represent sensor data
    static class SensorData {
        private String temperature;
        private String humidity;
        // Getters and setters
        public String getTemperature() {
            return temperature;
        }
        public void setTemperature(String temperature) {
            this.temperature = temperature;
        }
        public String getHumidity() {
            return humidity;
        }
        public void setHumidity(String humidity) {
            this.humidity = humidity;
        }
    }
}