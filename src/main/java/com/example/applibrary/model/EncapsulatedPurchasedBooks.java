package com.example.applibrary.model;

public class EncapsulatedPurchasedBooks {

    private int id;
    private String book;
    private int price;
    private String day;

    public void setId(int id) {
        this.id = id;
    }

    public void setBook(String book) {
        this.book = book;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public int getId() {
        return id;
    }

    public String getBook() {
        return book;
    }

    public int getPrice() {
        return price;
    }

    public String getDay() {
        return day;
    }

    public EncapsulatedPurchasedBooks(int id, String book, int price, String day) {
        this.id = id;
        this.book = book;
        this.price = price;
        this.day = day;
    }
}
