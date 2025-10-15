package com.github.lexya.entity;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

public class Order {
    private final String orderId;
    private final LocalDateTime orderDate;
    private final Customer customer;
    private final List<OrderItem> items;
    private final OrderStatus status;

    public Order(String orderId, LocalDateTime orderDate, Customer customer, List<OrderItem> items, OrderStatus status) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.customer = customer;
        this.items = items;
        this.status = status;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public String getOrderId() {
        return orderId;
    }

    public LocalDateTime getOrderDate() {
        return this.orderDate;
    }


    public Customer getCustomer() {
        return customer;
    }

    public List<OrderItem> getOrderItems() {
        return new LinkedList<>(items);
    }

}
