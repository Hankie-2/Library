package com.example.applibrary.model;

public class EncapsulatedReadedBooks {

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

    public EncapsulatedReadedBooks(int id, String book) {
        this.id = id;
        this.book = book;
    }
    
    
    @Override
    public String toString() {
        return this.getBook();
    }
}
