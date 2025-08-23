// 代码生成时间: 2025-08-23 11:47:47
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@SpringBootApplication
@RestController
@RequestMapping("/api")
public class ShoppingCartService {

    private Map<Integer, CartItem> cartItems = new HashMap<>();
    private AtomicInteger cartItemId = new AtomicInteger(1);

    public static void main(String[] args) {
        SpringApplication.run(ShoppingCartService.class, args);
    }

    @PostMapping("/add")
    public ResponseEntity<CartItem> addCartItem(@RequestBody CartItem cartItem) {
        if (cartItem == null || cartItem.getProductId() == null) {
            throw new IllegalArgumentException("Product ID is required");
        }
        cartItems.put(cartItemId.getAndIncrement(), cartItem);
        return ResponseEntity.ok(cartItem);
    }

    @GetMapping("/items")
    public ResponseEntity<Map<Integer, CartItem>> getAllCartItems() {
        return ResponseEntity.ok(cartItems);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    static class CartItem {
        private Integer productId;
        private Integer quantity;

        public CartItem() {
        }

        public Integer getProductId() {
            return productId;
        }

        public void setProductId(Integer productId) {
            this.productId = productId;
        }

        public Integer getQuantity() {
            return quantity;
        }

        public void setQuantity(Integer quantity) {
            this.quantity = quantity;
        }
    }
}
