package com.github.lexya;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class OrdersStructure {
    public class Order {
        private String orderId;
        private LocalDateTime orderDate;
        private Customer customer;
        private List<OrderItem> items;
        private OrderStatus status;

        public Order(String orderId, LocalDateTime orderDate, Customer customer, List<OrderItem> items, OrderStatus status) {
            this.orderId = orderId;
            this.orderDate = orderDate;
            this.customer = customer;
            this.items = items;
            this.status = status;
        }
        public Customer getCustomer() {
            return customer.clone();
        }

        public List<OrderItem> getOrderItems() {
            List<OrderItem> orderItems = new LinkedList<>();
            for (OrderItem item : items) {
                orderItems.add(item.clone());
            }
            return orderItems;
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


    }

    public class OrderItem implements Cloneable{
        private String productName;
        private int quantity;
        private double price;
        private Category category;

        public OrderItem(String productName, int quantity, double price, Category category) {
            this.productName = productName;
            this.quantity = quantity;
            this.price = price;
            this.category = category;
        }
        @Override
        public OrderItem clone() {
            try {
                return (OrderItem) super.clone();
            } catch (CloneNotSupportedException e) {
                throw new RuntimeException(e);
            }
        }

        public double getOrderItemPrice(){
            return price;
        }

        public String getOrderItemName(){
            return productName;
        }

        public int getQuantity() {
            return quantity;
        }

        public Category getCategory() {
            return category;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public double getPrice() {
            return price;
        }

    }

    public class Customer implements Cloneable {
        private String customerId;
        private String name;
        private String email;
        private LocalDateTime registeredAt;
        private int age;
        private String city;

        public Customer(String customerId, String name, String email, LocalDateTime registeredAt, int age, String city) {
            this.customerId = customerId;
            this.name = name;
            this.email = email;
            this.registeredAt = registeredAt;
            this.age = age;
            this.city = city;
        }

        @Override
        public Customer clone() {
            try {
                return (Customer) super.clone();
            } catch (CloneNotSupportedException e) {
                throw new RuntimeException(e);
            }
        }

        public String getCustomerId() {
            return customerId;
        }
        public String getName() {
            return name;
        }

        public String getEmail() {
            return email;
        }

        public LocalDateTime getRegisteredAt() {
            return registeredAt;
        }

        public int getAge() {
            return age;
        }

        public String getCity(){
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof Customer cust)) {
                return false;
            }
            return customerId.equals(cust.getCustomerId());
        }

        @Override
        public int hashCode() {
            return Objects.hash(customerId);
        }
    }

    public enum OrderStatus {
        NEW, PROCESSING, SHIPPED, DELIVERED, CANCELLED
    }

    public enum Category {
        ELECTRONICS, CLOTHING, BOOKS, HOME, BEAUTY, TOYS
    }
}
