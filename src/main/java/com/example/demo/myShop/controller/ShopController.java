package com.example.demo.myShop.controller;

import com.example.demo.myShop.service.ShopService;
import com.example.demo.myShop.exception.ShopExistException;
import com.example.demo.myShop.exception.ShopNotFoundException;
import com.example.demo.myShop.model.Shop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class ShopController {

    private final ShopService shopService;

    @Autowired
    public ShopController(ShopService shopService) {
        this.shopService = shopService;
    }

    @PostMapping(value = "/create",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createShop(@RequestBody Shop shop) {
        try {
            Shop newShop = shopService.createShop(shop);

            return new ResponseEntity<>("Created success " + newShop, HttpStatus.CREATED);
        } catch (ShopExistException exception) {

            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/shops")
    public ArrayList<Shop> getShops() {
        return shopService.getShops();
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<String> findShopById(@PathVariable("id") long id) throws ShopNotFoundException {
        try {
            Shop shop = shopService.findById(id);

            return new ResponseEntity<>(shop.toString(), HttpStatus.OK);
        } catch (ShopNotFoundException exception) {

            return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delShopById(@PathVariable("id") long id) throws ShopNotFoundException {
        try {
            shopService.delete(id);

            return new ResponseEntity<>("Delete success", HttpStatus.OK);
        } catch (ShopNotFoundException exception) {

            return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updateShop(@RequestBody Shop shop) {
        try {
            Shop shopUpdate = shopService.update(shop);

            return new ResponseEntity<>("Update success" + shopUpdate, HttpStatus.OK);
        } catch (ShopNotFoundException exception) {

            return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception exception) {
            System.err.println(exception.getMessage());
            return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
