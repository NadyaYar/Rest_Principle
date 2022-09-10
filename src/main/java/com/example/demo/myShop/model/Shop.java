package com.example.demo.myShop.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
public class Shop {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    private String city;

    private String street;

    private String name;

    private int numberOfStaff;

    private boolean isHasSite;

    public Shop(String city, String street, String name, int numberOfStaff, boolean isHasSite) {
        this.city = city;
        this.street = street;
        this.name = name;
        this.numberOfStaff = numberOfStaff;
        this.isHasSite = isHasSite;
    }
}
