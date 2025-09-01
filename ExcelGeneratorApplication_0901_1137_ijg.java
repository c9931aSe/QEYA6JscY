// 代码生成时间: 2025-09-01 11:37:21
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.io.IOException;
import java.io.OutputStream;
import javax.servlet.http.HttpServletResponse;

@SpringBootApplication
@RestController
@RequestMapping("/api/excel")
public class ExcelGeneratorApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExcelGeneratorApplication.class, args);
    }

    @GetMapping("/generate")
    public ResponseEntity<byte[]> generateExcel() {
        try {
            Workbook workbook = new XSSFWorkbook();
            // 在这里添加生成Excel的逻辑
            // 比如创建sheet，写入数据等

            byte[] excelData = toExcelByteArray(workbook);
            return ResponseEntity.ok().headers(createExcelHeaders()).body(excelData);
        } catch (IOException e) {
            // 异常处理逻辑
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    private byte[] toExcelByteArray(Workbook workbook) throws IOException {
        byte[] bytes;
        try (OutputStream outputStream = new ByteArrayOutputStream()) {
            workbook.write(outputStream);
            bytes = outputStream.toByteArray();
        }
        return bytes;
    }

    private HttpHeaders createExcelHeaders() {
        // 设置响应头信息
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=ExcelReport.xlsx");
        headers.add("Content-Type", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        return headers;
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        // 异常处理返回信息
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: \\