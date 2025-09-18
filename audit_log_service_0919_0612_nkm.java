// 代码生成时间: 2025-09-19 06:12:59
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

@SpringBootApplication
public class AuditLogServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuditLogServiceApplication.class, args);
    }

    @Bean
    public AuditLogInterceptor auditLogInterceptor() {
        return new AuditLogInterceptor();
    }
}

@RestController
class AuditLogController {

    @GetMapping("/log")
    public String log() {
        // 业务逻辑处理
        return "Logged";
    }
}

class AuditLogInterceptor extends HandlerInterceptorAdapter {
    private final CopyOnWriteArrayList<String> auditLogs = new CopyOnWriteArrayList<>();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uuid = UUID.randomUUID().toString();
        request.setAttribute("uuid", uuid);
        auditLogs.add("[PreHandle] Request ID: " + uuid + ", URI: " + request.getRequestURI());
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        String uuid = (String) request.getAttribute("uuid");
        if (ex != null) {
            auditLogs.add("[Error] Request ID: " + uuid + ", Exception: " + ex.getMessage());
        } else {
            auditLogs.add("[AfterCompletion] Request ID: " + uuid + ", Status: " + response.getStatus());
        }
    }
}

class ExceptionControllerAdvice {
    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    public String handleException(Exception e) {
        // 日志记录异常信息
        // 返回错误信息给客户端
        return "Error: " + e.getMessage();
    }
}