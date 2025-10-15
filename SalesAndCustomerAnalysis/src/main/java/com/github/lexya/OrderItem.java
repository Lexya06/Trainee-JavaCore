package com.github.lexya;

public class OrderItem {
    private String productName;
    private int quantity;
    private double price;
    private OrderCategory OrderCategory;

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

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

}
