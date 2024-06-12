import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ShopServiceTest {

    @Test
    void addOrderTest() {
        //GIVEN
        ShopService shopService = new ShopService();
        List<String> productsIds = List.of("1");

        //WHEN
        Order actual = shopService.addOrder(productsIds);

        //THEN
        Order expected = new Order("-1", List.of(new Product("1", "Apfel")), OrderStatus.PROCESSING);
        assertEquals(expected.products(), actual.products());
        assertNotNull(expected.id());
    }

    @Test
    void addOrderTest_whenInvalidProductId_expectProductNotFoundException() {
        //GIVEN
        ShopService shopService = new ShopService();
        List<String> productsIds = List.of("1", "2");

        //WHEN
        //THEN
        assertThrows(ProductNotFoundException.class, () -> shopService.addOrder(productsIds));
    }

    @Test
    void getOrdersTest() {
        //GIVEN
        ShopService shopService = new ShopService();
        List<String> productsIds = List.of("1");

        //WHEN
        Order actual = shopService.addOrder(productsIds);

        //THEN
        List<Order> orderList = shopService.getOrders(OrderStatus.PROCESSING);
        Order expected = orderList.getFirst();

        assertEquals(actual, expected);
    }

    @Test
    void getOrdersTest_whenOrderStatusIsNowhereSet_shouldReturnNull() {
        //GIVEN
        ShopService shopService = new ShopService();
        List<String> productsIds = List.of("1");

        //WHEN
        shopService.addOrder(productsIds);

        //THEN
        List<Order> orderList = shopService.getOrders(OrderStatus.COMPLETED);

        assertTrue(orderList.isEmpty());
    }

    @Test
    void updateOrderTest() {
        //GIVEN
        ShopService shopService = new ShopService();
        List<String> productsIds = List.of("1");

        //WHEN
        Order actual = shopService.addOrder(productsIds);

        //THEN
        shopService.updateOrder(actual.id(), OrderStatus.IN_DELIVERY);
        System.out.println(OrderStatus.IN_DELIVERY);

        actual = shopService.getOrders(OrderStatus.IN_DELIVERY).getFirst();

        assertEquals(actual.status().name(), OrderStatus.IN_DELIVERY.name());
    }
}
