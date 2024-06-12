import lombok.With;

import java.time.LocalDateTime;
import java.util.List;

@With
public record Order(
        String id,
        List<Product> products,
        OrderStatus status,
        LocalDateTime timestamp
) {
}
