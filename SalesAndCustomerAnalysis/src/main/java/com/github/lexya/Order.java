package com.github.lexya;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

public class Order {
    private final String orderId;
    private final LocalDateTime orderDate;
    private final Customer customer;
    private List<OrderItem> items;
    private OrderStatus status;

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

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public String getOrderId() {
        return orderId;
    }

    public LocalDateTime getOrderDate(LocalDateTime orderDate) {
        return this.orderDate;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.items = orderItems;
    }

    public Customer getCustomer() {
        return customer;
    }

    public List<OrderItem> getOrderItems() {
        return new LinkedList<>(items);
    }

}
