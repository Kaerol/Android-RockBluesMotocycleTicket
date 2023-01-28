package com.appliti.ticket.service.response;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Order {

    private final int id;

    public int getId() {
        return id;
    }

    public String getAction() {
        return action;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getMail() {
        return mail;
    }

    public String getPhone() {
        return phone;
    }

    public int getReleased() {
        return released;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }

    private final String action;
    private final String firstName;
    private final String lastName;
    private final String mail;
    private final String phone;
    private final BigDecimal totalValue;
    private final int released;
    private final ArrayList<Product> products;
    private final ArrayList<Comment> comments;

    public Order(int id, String action, String firstName, String lastName, String mail, String phone, BigDecimal totalValue, int released, ArrayList<Product> products, ArrayList<Comment> comments) {
        this.id = id;
        this.action = action;
        this.firstName = firstName;
        this.lastName = lastName;
        this.mail = mail;
        this.phone = phone;
        this.totalValue = totalValue;
        this.released = released;
        this.products = products;
        this.comments = comments;
    }

    public BigDecimal getTotalValue() {
        BigDecimal total = BigDecimal.ZERO;

        for (final Product product : products) {
            if (product.isVirtual()) {
                total = total.add(product.getValue());
            }
        }
        return total;
    }
}
