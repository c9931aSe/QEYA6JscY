// 代码生成时间: 2025-08-04 09:08:19
package com.example.performancemonitoring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@RestController
@RequestMapping("/monitor")
public class PerformanceMonitoringApp {

    private static final String MBEAN_NAME = "java.lang:type=OperatingSystem";

    private MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();

    public static void main(String[] args) {
        SpringApplication.run(PerformanceMonitoringApp.class, args);
    }

    @GetMapping("/metrics")
    public Map<String, Object> getSystemMetrics() {
        Map<String, Object> metrics = new HashMap<>();
        try {
            metrics.put("FreePhysicalMemorySize", mBeanServer.getAttribute(new ObjectName(MBEAN_NAME), "FreePhysicalMemorySize"));
            metrics.put("TotalPhysicalMemorySize", mBeanServer.getAttribute(new ObjectName(MBEAN_NAME), "TotalPhysicalMemorySize"));
            metrics.put("SystemCpuLoad", mBeanServer.getAttribute(new ObjectName(MBEAN_NAME), "SystemCpuLoad"));
            metrics.put("ProcessCpuLoad", mBeanServer.getAttribute(new ObjectName(MBEAN_NAME), "ProcessCpuLoad"));
        } catch (Exception e) {
            throw new RuntimeException("Error fetching system metrics", e);
        }
        return metrics;
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(value = org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, String> handleRuntimeException(RuntimeException ex) {
        Map<String, String> errorDetails = new HashMap<>();
        errorDetails.put("message", ex.getMessage());
        return errorDetails;
    }
}