package com.appliti.ticket.service.response;

import java.math.BigDecimal;

public class Product {
    private final String name;
    private final int count;

    public String getName() {
        return name;
    }

    public int getCount() {
        return count;
    }

    public boolean isVirtual() {
        return virtual;
    }

    public BigDecimal getValue() {
        return value;
    }

    private final boolean virtual;
    private final BigDecimal value;

    public Product(String name, int count, boolean virtual, BigDecimal value) {
        this.name = name;
        this.count = count;
        this.virtual = virtual;
        this.value = value;
    }
}
