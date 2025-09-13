// 代码生成时间: 2025-09-13 14:52:45
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.kafka.support.ProducerFailureCallback;
import org.springframework.kafka.support.ProducerRecordBuilder;
import java.util.concurrent.ExecutionException;
import org.springframework.kafka.support.SendResult;

@RestController
@RequestMapping("/api/notifications")
public class NotificationService {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    // Sends a notification message to a Kafka topic
    @PostMapping("/send")
    public ListenableFuture<SendResult<String, String>> sendNotification(@RequestBody NotificationMessage message) {
        return kafkaTemplate.send(
            ProducerRecordBuilder.<String, String>
                .withTopic("notifications")
                .withKey(message.getRecipient())
                .withValue(message.getMessage())
                .build()
        )
        .addCallback(new ProducerFailureCallback() {
            @Override
            public void onError(ProducerRecord<String, String> record, Exception exception) {
                // Handle the failure
                System.out.println("Failed to send notification: " + exception.getMessage());
            }
        });
    }

    // REST API to get notification status
    @GetMapping("/status")
    public ResponseEntity<String> getStatus() {
        return ResponseEntity.ok("Notification service is running.");
    }
}

@Service
class NotificationServiceService {
    // Service layer methods go here
}

@ControllerAdvice
class GlobalExceptionHandler {
    // Handle exceptions globally
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Object> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        return new ResponseEntity<>("Error: The request payload cannot be read.", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAllExceptions(Exception e) {
        return new ResponseEntity<>("Error: An unexpected error occurred.", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

class NotificationMessage {
    private String recipient;
    private String message;

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
