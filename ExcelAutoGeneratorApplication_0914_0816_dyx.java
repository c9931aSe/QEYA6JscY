// 代码生成时间: 2025-09-14 08:16:45
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.ResponseEntity;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseStatus;

@SpringBootApplication
@RestController
@RequestMapping("/api/excel")
public class ExcelAutoGeneratorApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(ExcelAutoGeneratorApplication.class, args);
    }
    
    @GetMapping("/generate")
    public ResponseEntity<Workbook> generateExcel() {
        try {
            Workbook workbook = new XSSFWorkbook();
            // Add your logic to populate the workbook here
            // Return the workbook as a response
            return ResponseEntity.ok(workbook);
        } catch (Exception e) {
            // Log the exception
            // Return a bad request response with the error message
            return ResponseEntity.badRequest().body(null);
        }
    }
    
    @ControllerAdvice
    class GlobalExceptionHandler {
        @ExceptionHandler(Exception.class)
        @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
        public String handleException(Exception e) {
            // Log the exception
            return "An error occurred: " + e.getMessage();
        }
    }
}
