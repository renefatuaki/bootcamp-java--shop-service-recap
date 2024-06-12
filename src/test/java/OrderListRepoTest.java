import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderListRepoTest {
    private final LocalDateTime timestamp = LocalDateTime.now();
    private final OrderListRepo repo = new OrderListRepo();
    private final Product product = new Product("1", "Apfel");
    private final Order newOrder = new Order("1", List.of(product), OrderStatus.PROCESSING, timestamp);
    private Order actual;

    @BeforeEach
    void setUpBefore() {
        actual = repo.addOrder(newOrder);
    }

    @AfterEach
    void setUpAfter() {
        List<String> orderIds = repo.getOrders().stream().map(Order::id).toList();

        for (String id : orderIds) repo.removeOrder(id);
    }

    @Test
    void getOrders() {
        //WHEN
        List<Order> actual = repo.getOrders();

        //THEN
        List<Order> expected = new ArrayList<>();
        Product product1 = new Product("1", "Apfel");
        expected.add(new Order("1", List.of(product1), OrderStatus.PROCESSING, timestamp));

        assertEquals(actual, expected);
    }

    @Test
    void getOrderById() {
        //WHEN
        Order actual = repo.getOrderById("1");

        //THEN
        Product product1 = new Product("1", "Apfel");
        Order expected = new Order("1", List.of(product1), OrderStatus.PROCESSING, timestamp);

        assertEquals(actual, expected);
    }

    @Test
    void addOrder() {
        //THEN
        Product product1 = new Product("1", "Apfel");
        Order expected = new Order("1", List.of(product1), OrderStatus.PROCESSING, timestamp);
        assertEquals(actual, expected);
        assertEquals(repo.getOrderById("1"), expected);
    }

    @Test
    void removeOrder() {
        repo.removeOrder("1");

        assertNull(repo.getOrderById("1"));
    }
}
