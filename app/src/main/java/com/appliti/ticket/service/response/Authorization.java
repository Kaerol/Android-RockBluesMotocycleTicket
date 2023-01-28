package com.appliti.ticket.service.response;

public class Authorization {
    public String getCode() {
        return code;
    }

    private final String code;

    public Authorization(String code) {
        this.code = code;
    }
}
