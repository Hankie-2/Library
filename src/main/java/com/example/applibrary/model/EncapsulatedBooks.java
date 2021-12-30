package com.example.applibrary.model;

public class EncapsulatedBooks {
    private int id;
    private String book;
    private String author;
    private int publicate;
    private int sellCost;
    private int rentCost;
    private int amount;

    public void setId(int id) {
        this.id = id;
    }

    public void setBook(String book) {
        this.book = book;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPublicate(int publicate) {
        this.publicate = publicate;
    }

    public void setSellCost(int sellCost) {
        this.sellCost = sellCost;
    }

    public void setRentCost(int rentCost) {
        this.rentCost = rentCost;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public String getBook() {
        return book;
    }

    public String getAuthor() {
        return author;
    }

    public int getPublicate() {
        return publicate;
    }

    public int getSellCost() {
        return sellCost;
    }

    public int getRentCost() {
        return rentCost;
    }

    public int getAmount() {
        return amount;
    }

    public EncapsulatedBooks(int id, String book, String author, int publicate, int sellCost, int rentCost, int amount) {
        this.id = id;
        this.book = book;
        this.author = author;
        this.publicate = publicate;
        this.sellCost = sellCost;
        this.rentCost = rentCost;
        this.amount = amount;
    }
    
    @Override
    public String toString() {
        return this.getBook();
    }
}
