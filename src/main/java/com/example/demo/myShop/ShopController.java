package com.example.demo.myShop;

import com.example.demo.myShop.exception.BadRequestException;
import com.example.demo.myShop.exception.UserNotFoundException;
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
        } catch (BadRequestException e) {

            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/shops")
    public ArrayList<Shop> getShops() {
        return shopService.getShops();
    }

    @GetMapping("/findById{id}")
    public ResponseEntity<String> findShopById(@PathVariable("id") long id) throws UserNotFoundException {
        try {
            Shop shop = shopService.findById(id);

            return new ResponseEntity<>(shop.toString(), HttpStatus.FOUND);
        } catch (UserNotFoundException e) {

            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete{id}")
    public ResponseEntity<String> delShopById(@PathVariable("id") long id) throws UserNotFoundException {
        try {
            shopService.delete(id);

            return new ResponseEntity<>("Delete success", HttpStatus.OK);
        } catch (UserNotFoundException e) {

            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<String> update(@RequestBody Shop shop) {
        try {
            Shop shopUpdate = shopService.update(shop);

            return new ResponseEntity<>("Update success" + shopUpdate, HttpStatus.OK);
        } catch (UserNotFoundException e) {

            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
