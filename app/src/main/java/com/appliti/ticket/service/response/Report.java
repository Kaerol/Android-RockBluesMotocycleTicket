package com.appliti.ticket.service.response;

import java.util.List;

public class Report {
    public List<Product> getWaiting() {
        return waiting;
    }

    public List<Product> getReleased() {
        return released;
    }

    private final List<Product> waiting;
    private final List<Product> released;

    public Report(List<Product> waiting, List<Product> released) {
        this.waiting = waiting;
        this.released = released;
    }
}
