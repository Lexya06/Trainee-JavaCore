package com.github.lexya.entity;

public class OrderItem {
    private final String productName;
    private final int quantity;
    private final double price;
    private final OrderCategory OrderCategory;

    public OrderItem(String productName, int quantity, double price, OrderCategory OrderCategory) {
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
        this.OrderCategory = OrderCategory;
    }

    public double getOrderItemPrice() {
        return price;
    }

    public String getOrderItemName() {
        return productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public OrderCategory getOrderCategory() {
        return OrderCategory;
    }

    public double getPrice() {
        return price;
    }

}
