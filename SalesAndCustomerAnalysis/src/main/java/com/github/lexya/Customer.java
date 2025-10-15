package com.github.lexya;

import java.time.LocalDateTime;
import java.util.Objects;

public class Customer {
    private final String customerId;
    private  String name;
    private  String email;
    private final LocalDateTime registeredAt;
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

    public String getCity() {
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

