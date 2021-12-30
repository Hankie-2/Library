package com.example.applibrary.model;

public class EncapsulatedFavoriteBooks {

    private int id;
    private String book;

    public void setId(int id) {
        this.id = id;
    }

    public void setBook(String book) {
        this.book = book;
    }

    public int getId() {
        return id;
    }

    public String getBook() {
        return book;
    }

    public EncapsulatedFavoriteBooks(int id, String book) {
        this.id = id;
        this.book = book;
    }
}
