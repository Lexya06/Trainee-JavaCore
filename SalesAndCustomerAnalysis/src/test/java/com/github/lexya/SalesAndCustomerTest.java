package com.github.lexya;
import com.github.lexya.OrdersStructure.*;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SalesAndCustomerTest {
    SalesAndCustomerAnalysis analysis;
    public SalesAndCustomerTest(){
        this.analysis = new SalesAndCustomerAnalysis(createTestOrders());
    }
    public static List<Order> createTestOrders() {
        List<Order> orders = new LinkedList<>();
        OrdersStructure factory = new OrdersStructure();

        // Clients creation
        Customer customer1 = factory.new Customer("CUST001", "Иван Иванов", "ivan@mail.ru",
                LocalDateTime.of(2022, 3, 15, 10, 0), 28, "Москва");
        Customer customer2 = factory.new Customer("CUST002", "Петр Петров", "petr@gmail.com",
                LocalDateTime.of(2021, 7, 20, 14, 30), 35, "Санкт-Петербург");
        Customer customer3 = factory.new Customer("CUST003", "Анна Сидорова", "anna@yandex.ru",
                LocalDateTime.of(2023, 1, 10, 9, 15), 24, "Москва");
        Customer customer4 = factory.new Customer("CUST004", "Мария Козлова", "maria@mail.ru",
                LocalDateTime.of(2020, 12, 5, 16, 45), 42, "Екатеринбург");
        Customer customer5 = factory.new Customer("CUST005", "Алексей Смирнов", "alex@google.com",
                LocalDateTime.of(2022, 9, 8, 11, 20), 31, "Екатеринбург");

        // Order 1 - Electronics (Delivered)
        List<OrderItem> items1 = List.of(
                factory.new OrderItem("iPhone 14", 1, 89990.0, Category.ELECTRONICS),
                factory.new OrderItem("Чехол для iPhone 14", 2, 1990.0, Category.ELECTRONICS)
        );
        orders.add(factory.new Order("ORD001",
                LocalDateTime.of(2024, 1, 15, 10, 30), customer1, items1, OrderStatus.DELIVERED));

        // Order 2 - Clothing (Processing)
        List<OrderItem> items2 = List.of(
                factory.new OrderItem("Джинсы", 1, 3490.0, Category.CLOTHING),
                factory.new OrderItem("Футболка", 3, 1290.0, Category.CLOTHING)
        );
        orders.add(factory.new Order("ORD002",
                LocalDateTime.of(2024, 1, 16, 14, 20), customer2, items2, OrderStatus.PROCESSING));

        // Order 3 - Books (Delivered)
        List<OrderItem> items3 = List.of(
                factory.new OrderItem("Java для начинающих", 1, 2450.0, Category.BOOKS),
                factory.new OrderItem("Алгоритмы и структуры данных", 1, 3200.0, Category.BOOKS)
        );
        orders.add(factory.new Order("ORD003",
                LocalDateTime.of(2024, 1, 17, 9, 45), customer3, items3, OrderStatus.DELIVERED));

        // Order 4 - House and electronics (Delivered)
        List<OrderItem> items4 = List.of(
                factory.new OrderItem("Кофемашина", 1, 45990.0, Category.ELECTRONICS),
                factory.new OrderItem("Кофейные чашки", 4, 890.0, Category.HOME)
        );
        orders.add(factory.new Order("ORD004",
                LocalDateTime.of(2024, 1, 18, 16, 10), customer4, items4, OrderStatus.DELIVERED));

        // Order 5 - Toys (New order)
        List<OrderItem> items5 = List.of(
                factory.new OrderItem("Конструктор LEGO", 2, 5490.0, Category.TOYS),
                factory.new OrderItem("Кукла", 1, 2190.0, Category.TOYS)
        );
        orders.add(factory.new Order("ORD005",
                LocalDateTime.of(2024, 1, 19, 11, 30), customer5, items5, OrderStatus.NEW));

        // Order 6 - Beauty (Cancelled)
        List<OrderItem> items6 = List.of(
                factory.new OrderItem("Помада", 3, 1490.0, Category.BEAUTY),
                factory.new OrderItem("Тушь для ресниц", 2, 1890.0, Category.BEAUTY)
        );
        orders.add(factory.new Order("ORD006",
                LocalDateTime.of(2024, 1, 20, 13, 15), customer1, items6, OrderStatus.CANCELLED));

        // Order 7 - Electronics (Delivered)
        List<OrderItem> items7 = List.of(
                factory.new OrderItem("AirPods", 5, 12990.0, Category.ELECTRONICS),
                factory.new OrderItem("Чехол для AirPods", 5, 990.0, Category.ELECTRONICS)
        );
        orders.add(factory.new Order("ORD007",
                LocalDateTime.of(2024, 1, 21, 15, 40), customer2, items7, OrderStatus.DELIVERED));

        // Order 8 - Books and clothing (Delivered)
        List<OrderItem> items8 = List.of(
                factory.new OrderItem("Война и мир", 1, 1890.0, Category.BOOKS),
                factory.new OrderItem("Свитер", 2, 4590.0, Category.CLOTHING)
        );
        orders.add(factory.new Order("ORD008",
                LocalDateTime.of(2024, 1, 22, 10, 5), customer3, items8, OrderStatus.DELIVERED));

        // Order 9 - Home (Shipped)
        List<OrderItem> items9 = List.of(
                factory.new OrderItem("Набор кастрюль", 1, 12990.0, Category.HOME),
                factory.new OrderItem("Сковорода", 2, 3490.0, Category.HOME)
        );
        orders.add(factory.new Order("ORD009",
                LocalDateTime.of(2024, 1, 23, 12, 25), customer4, items9, OrderStatus.SHIPPED));

        // Order 10 - Electronics (Delivered)
        List<OrderItem> items10 = List.of(
                factory.new OrderItem("AirPods", 3, 12990.0, Category.ELECTRONICS),
                factory.new OrderItem("MacBook Pro", 1, 199990.0, Category.ELECTRONICS)
        );
        orders.add(factory.new Order("ORD010",
                LocalDateTime.of(2024, 1, 24, 17, 50), customer5, items10, OrderStatus.DELIVERED));

        // Order 11 - Books (Delivered)
        List<OrderItem> items11 = List.of(
                factory.new OrderItem("The little prince", 1, 2800.0, OrdersStructure.Category.BOOKS),
                factory.new OrderItem("Spring Framework", 1, 3500.0, OrdersStructure.Category.BOOKS)
        );
        orders.add(factory.new Order("ORD011",
                LocalDateTime.of(2024, 1, 25, 8, 30), customer1, items11, OrdersStructure.OrderStatus.DELIVERED));

        // Order 12 - Electronics (Delivered)
        List<OrderItem> items12 = List.of(
                factory.new OrderItem("Мышь компьютерная", 2, 2490.0, OrdersStructure.Category.ELECTRONICS),
                factory.new OrderItem("Клавиатура", 1, 4590.0, OrdersStructure.Category.ELECTRONICS)
        );
        orders.add(factory.new Order("ORD012",
                LocalDateTime.of(2024, 1, 26, 11, 20), customer1, items12, OrdersStructure.OrderStatus.DELIVERED));

        // Order 13 - Clothing (Processing)
        List<OrderItem> items13 = List.of(
                factory.new OrderItem("Куртка", 1, 8990.0, OrdersStructure.Category.CLOTHING),
                factory.new OrderItem("Шапка", 1, 1290.0, OrdersStructure.Category.CLOTHING)
        );
        orders.add(factory.new Order("ORD013",
                LocalDateTime.of(2024, 1, 27, 14, 45), customer1, items13, OrdersStructure.OrderStatus.PROCESSING));

        // Order 14 - House (Delivered)
        List<OrderItem> items14 = List.of(
                factory.new OrderItem("Настольная лампа", 1, 3290.0, OrdersStructure.Category.HOME),
                factory.new OrderItem("Коврик для мыши", 3, 490.0, OrdersStructure.Category.HOME)
        );
        orders.add(factory.new Order("ORD014",
                LocalDateTime.of(2024, 1, 28, 16, 10), customer1, items14, OrdersStructure.OrderStatus.DELIVERED));

        // Order 15 - Electronics (New)
        List<OrderItem> items15 = List.of(
                factory.new OrderItem("Power Bank", 1, 3990.0, OrdersStructure.Category.ELECTRONICS),
                factory.new OrderItem("Кабель USB-C", 2, 890.0, OrdersStructure.Category.ELECTRONICS)
        );
        orders.add(factory.new Order("ORD015",
                LocalDateTime.of(2024, 1, 29, 9, 15), customer1, items15, OrdersStructure.OrderStatus.NEW));

        return orders;
    }

    @Test
    public void testUniqueCustomerCities(){
        List<String> uniqueCities = analysis.calculateUniqueCustomerCities();
        assertEquals(3, uniqueCities.size());
        assertTrue(uniqueCities.containsAll(List.of("Москва", "Санкт-Петербург", "Екатеринбург")));
    }

    private double getPriceFromOrdersItemsList(int[] indexes){
        double price = 0;
        for (int index : indexes){
            List<OrderItem> items = analysis.getOrders().get(index).getOrderItems();
            for(OrderItem orderItem : items){
                price += orderItem.getPrice() * orderItem.getQuantity();
            }
        }
        return price;
    }

    @Test
    public void testTotalIncomeForCompletedOrders(){
        // Delivered orders : 1, 3, 4, 7, 8, 11, 12, 14
        int[] indexes = {0,2,3,6,7,9,10,11,13};

        double expectedPrice = getPriceFromOrdersItemsList(indexes);

        // Expected price calculated from all delivered orders (items from them)
        assertEquals(expectedPrice,analysis.totalCompletedOrdersIncome());
    }

    @Test
    public void theMostPopularProduct(){
        assertEquals("AirPods",analysis.theMostPopularProductName());
    }

    @Test
    public void testGetCustomersByOrdersCount(){
        List<Customer> customers = analysis.getCustomersByOrdersCount(5);
        assertEquals(1, customers.size());
        assertEquals("CUST001",customers.get(0).getCustomerId());
    }

    private double countDeliveredOrdersPercentage(){
        int countDelivered = 0;
        for(Order order:analysis.getOrders()){
            if(order.getStatus() == OrdersStructure.OrderStatus.DELIVERED){
                countDelivered++;
            }
        }
        return (double) countDelivered / analysis.getOrders().size();
    }

    @Test
    public void testDeliveredOrdersPercentage(){
        assertEquals(countDeliveredOrdersPercentage(), analysis.deliveredOrdersPercentage());
    }



}
