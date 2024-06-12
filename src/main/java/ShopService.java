import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ShopService {
    private final ProductRepo productRepo = new ProductRepo();
    private final OrderRepo orderRepo = new OrderMapRepo();

    public Order addOrder(List<String> productIds) throws ProductNotFoundException {
        List<Product> products = new ArrayList<>();
        for (String productId : productIds) {
            Optional<Product> productToOrder = productRepo.getProductById(productId);

            products.add(productToOrder.orElseThrow(() -> new ProductNotFoundException("Product not found!")));

        }

        Order newOrder = new Order(UUID.randomUUID().toString(), products, OrderStatus.PROCESSING);

        return orderRepo.addOrder(newOrder);
    }

    // Write a method in the ShopService that returns a list of all orders with a specific order status (parameter) using streams.
    public List<Order> getOrders(OrderStatus status) {
        return orderRepo.getOrders().stream().filter(order -> order.status() == status).toList();
    }
}
