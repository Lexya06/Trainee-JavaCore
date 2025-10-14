package com.github.lexya;
import com.github.lexya.OrdersStructure.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class SalesAndCustomerAnalysis {

    private List<Order> orders;
    public SalesAndCustomerAnalysis(List<Order> orders) {
        this.orders = orders;
    }
    public List<String> calculateUniqueCustomerCities(){
        return orders.stream().map(order -> order.getCustomer().getCity()).distinct().collect(Collectors.toList());
    }

    public double totalCompletedOrdersIncome(){
        return orders.stream().filter(order-> order.getStatus() == OrderStatus.DELIVERED).flatMap(order->order.getOrderItems().stream()).mapToDouble(item -> item.getOrderItemPrice() * item.getQuantity()).sum();
    }

    public String theMostPopularProductName(){
        Map<String, Integer> productsAndCounts;
        productsAndCounts = orders.stream().flatMap(order->order.getOrderItems().stream()).collect(Collectors.toMap(OrderItem::getOrderItemName,OrderItem::getQuantity,Integer::sum, HashMap::new));
        return productsAndCounts.entrySet().stream().max(Map.Entry.comparingByValue()).map(Map.Entry::getKey).orElse(null);
    }

    public List<Customer> getCustomersByOrdersCount(int ordersCount){
        Map<String, Long> customersIdAndOrders;
        customersIdAndOrders = orders.stream().map(order->order.getCustomer().getCustomerId()).collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
        List<String> customersFilterId = customersIdAndOrders.entrySet().stream().filter(entry -> entry.getValue() > ordersCount).map(Map.Entry::getKey).collect(Collectors.toList());

        return orders.stream().map(Order::getCustomer).filter(customer -> customersFilterId.contains(customer.getCustomerId())).distinct().collect(Collectors.toList());
    }

    public double deliveredOrdersPercentage(){
        long deliveredOrdersCount = orders.stream().filter(order->order.getStatus() == OrderStatus.DELIVERED).count();
        return (double)deliveredOrdersCount / orders.size();
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public List<Order> getOrders(){
        return this.orders;
    }
}