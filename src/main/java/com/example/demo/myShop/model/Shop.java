package com.example.demo.myShop.model;

public class Shop {
    private long id;
    private String city;
    private String street;
    private String name;
    private int numberOfStaff;
    private boolean isHasSite;

    public Shop(long id, String city, String street, String name, int numberOfStaff, boolean isHasSite) {
        this.id = id;
        this.city = city;
        this.street = street;
        this.name = name;
        this.numberOfStaff = numberOfStaff;
        this.isHasSite = isHasSite;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberOfStaff() {
        return numberOfStaff;
    }

    public void setNumberOfStaff(int numberOfStaff) {
        this.numberOfStaff = numberOfStaff;
    }

    public boolean isHasSite() {
        return isHasSite;
    }

    public void setHasSite(boolean hasSite) {
        isHasSite = hasSite;
    }

}
