package com.example.applibrary.model;

import java.util.Date;

public class EncapsulatedRentedBooks {

    private int id;
    private String login;
    private String name;
    private int rentCost;
    private Date startDate;
    private Date expirationDate;

    public EncapsulatedRentedBooks(int id, String login, String name, int rentCost, Date startDate, Date expirationDate) {
        this.id = id;
        this.login = login;
        this.name = name;
        this.rentCost = rentCost;
        this.startDate = startDate;
        this.expirationDate = expirationDate;
    }

    public EncapsulatedRentedBooks(int id, String name, int rentCost, Date startDate, Date expirationDate) {
        this.id = id;
        this.name = name;
        this.rentCost = rentCost;
        this.startDate = startDate;
        this.expirationDate = expirationDate;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRentCost(int rentCost) {
        this.rentCost = rentCost;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public int getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getName() {
        return name;
    }

    public int getRentCost() {
        return rentCost;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

}