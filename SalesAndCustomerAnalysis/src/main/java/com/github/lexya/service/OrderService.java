package com.github.lexya.service;

import com.github.lexya.entity.Customer;
import com.github.lexya.entity.Order;
import com.github.lexya.entity.OrderItem;
import com.github.lexya.entity.OrderStatus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class OrderService {

    public OrderService() {

    }

    public List<String> calculateUniqueCustomerCities(List<Order> orders) {
        return orders.stream().map(order -> order.getCustomer().getCity()).distinct().collect(Collectors.toList());
    }

    public double totalCompletedOrdersIncome(List<Order> orders) {
        return orders.stream().filter(order -> order.getStatus() == OrderStatus.DELIVERED).flatMap(order -> order.getOrderItems().stream()).mapToDouble(item -> item.getOrderItemPrice() * item.getQuantity()).sum();
    }

    public String theMostPopularProductName(List<Order> orders) {
        Map<String, Integer> productsAndCounts;
        productsAndCounts = orders.stream().flatMap(order -> order.getOrderItems().stream()).collect(Collectors.toMap(OrderItem::getOrderItemName, OrderItem::getQuantity, Integer::sum, HashMap::new));
        return productsAndCounts.entrySet().stream().max(Map.Entry.comparingByValue()).map(Map.Entry::getKey).orElse(null);
    }

    public List<Customer> getCustomersByOrdersCount(List<Order> orders, int ordersCount) {
        Map<String, Long> customersIdAndOrders;
        customersIdAndOrders = orders.stream().map(order -> order.getCustomer().getCustomerId()).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        List<String> customersFilterId = customersIdAndOrders.entrySet().stream().filter(entry -> entry.getValue() > ordersCount).map(Map.Entry::getKey).toList();

        return orders.stream().map(Order::getCustomer).filter(customer -> customersFilterId.contains(customer.getCustomerId())).distinct().collect(Collectors.toList());
    }

    public double deliveredOrdersPercentage(List<Order> orders) {
        long deliveredOrdersCount = orders.stream().filter(order -> order.getStatus() == OrderStatus.DELIVERED).count();
        return (double) deliveredOrdersCount / orders.size();
    }
}