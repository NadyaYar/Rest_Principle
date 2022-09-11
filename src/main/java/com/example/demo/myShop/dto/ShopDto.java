package com.example.demo.myShop.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class ShopDto {
    private long id;

    private String city;

    private String street;

    private String name;

    private Boolean isHasSite;
}
