package com.example.applibrary.model;

public class EncapsulatedUsers {

    private int id;
    private String password;
    private String login;
    private int wallet;
    private Integer accType;
    private String name;
    private String surname;

    public EncapsulatedUsers() {

    }

    public Integer getAccType() {
        return accType;
    }

    public void setAccType(Integer accType) {
        this.accType = accType;
    }

    public int getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public String getLogin() {
        return login;
    }

    public int getWallet() {
        return wallet;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setWallet(int wallet) {
        this.wallet = wallet;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public EncapsulatedUsers(int id, String name, String surname, String login, int wallet, String password) {
        this.id = id;
        this.password = password;
        this.login = login;
        this.wallet = wallet;
        this.name = name;
        this.surname = surname;
    }

}
