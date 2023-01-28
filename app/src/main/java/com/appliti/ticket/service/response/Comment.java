package com.appliti.ticket.service.response;



public class Comment {
    public String getContent() {
        return content;
    }

    public String getAuthor() {
        return author;
    }

    public String getDate() {
        return date;
    }

    private final String content;
    private final String author;
    private final String date;

    public Comment(String content, String author, String date) {
        this.content = content;
        this.author = author;
        this.date = date;
    }
}