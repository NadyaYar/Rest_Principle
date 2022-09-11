package com.example.demo.myShop.controller;

import com.example.demo.myShop.dto.ShopDto;
import com.example.demo.myShop.service.ShopService;
import com.example.demo.myShop.exception.ShopNotFoundException;
import com.example.demo.myShop.model.Shop;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
public class ShopController {

    private final ShopService shopService;

    @SneakyThrows
    @PostMapping(value = "/createShop")
    public Shop createShop(HttpServletRequest httpServletRequest) {

        BufferedReader bufferedReader = httpServletRequest.getReader();
        String shopJson = bufferedReader.lines().collect(Collectors.joining());

        ObjectMapper objectMapper = new ObjectMapper();
        Shop shop = objectMapper.readValue(shopJson, Shop.class);
        return shopService.createShop(shop);
    }

    @SneakyThrows
    @PostMapping(value = "/createShopDto")
    public ShopDto createShopDto(HttpServletRequest httpServletRequest) {


        BufferedReader bufferedReader = httpServletRequest.getReader();
        String shopJson = bufferedReader.lines().collect(Collectors.joining());

        ObjectMapper objectMapper = new ObjectMapper();
        ShopDto shopDto = objectMapper.readValue(shopJson, ShopDto.class);
        return shopService.createShopDto(shopDto);
    }

    @SneakyThrows
    @GetMapping(value = "/getAllShops")
    public void getShops(HttpServletResponse httpServletResponse) {
        PrintWriter writer = httpServletResponse.getWriter();
        shopService.getShops().forEach(e -> writer.println(e.toString()));
        writer.flush();
    }

    @GetMapping("/findById/{id}")
    public Shop findShopById(@PathVariable("id") long id) throws ShopNotFoundException {
        return shopService.findById(id);
    }

    @DeleteMapping("/delete/{id}")
    public void delShopById(@PathVariable("id") long id) throws ShopNotFoundException {
        shopService.delete(id);
    }

    @PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Shop updateShop(@RequestBody Shop shop, Long id) throws ShopNotFoundException {
        return shopService.update(shop, id);
    }
}
