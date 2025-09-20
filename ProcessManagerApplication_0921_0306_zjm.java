// 代码生成时间: 2025-09-21 03:06:56
@SpringBootApplication
@RestController
@RequestMapping("/api/processes")
public class ProcessManagerApplication {

    @PostMapping("/start")
    public ResponseEntity<?> startProcess(@RequestBody ProcessRequest processRequest) {
        try {
            // Logic to start a process based on the request parameters
            // For example:
            String processId = ProcessService.startProcess(processRequest.getCommand());
            return ResponseEntity.ok().body(new ProcessResponse(processId));
        } catch (Exception e) {
            // Exception handling
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse("Internal Server Error", e.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProcessDetails(@PathVariable("id") String processId) {
        try {
            ProcessDetails processDetails = ProcessService.getProcessDetails(processId);
            return ResponseEntity.ok().body(processDetails);
        } catch (ProcessNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("Process Not Found", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse("Internal Server Error", e.getMessage()));
        }
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse("Internal Server Error", e.getMessage()));
    }

    public static void main(String[] args) {
        SpringApplication.run(ProcessManagerApplication.class, args);
    }
}

// Supporting classes
class ProcessRequest {
    private String command;
    // Getters and setters
}

class ProcessResponse {
    private String processId;
    // Constructor, getters and setters
}

class ProcessDetails {
    private String id;
    private String status;
    // Additional fields
    // Getters and setters
}

class ErrorResponse {
    private String error;
    private String message;
    // Constructor, getters and setters
}

class ProcessNotFoundException extends RuntimeException {
    public ProcessNotFoundException(String message) {
        super(message);
    }
}
