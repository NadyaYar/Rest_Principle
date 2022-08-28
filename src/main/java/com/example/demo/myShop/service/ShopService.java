package com.example.demo.myShop.service;

import com.example.demo.myShop.exception.ShopExistException;
import com.example.demo.myShop.exception.ShopNotFoundException;
import com.example.demo.myShop.model.Shop;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ShopService {

    private final ArrayList<Shop> dataBaseShops = new ArrayList<>();

    public Shop createShop(Shop shop) throws ShopExistException {
        if (dataBaseShops.contains(shop)) {
            throw new ShopExistException("Shop with id: " + shop.getId() + " already exist");
        }
        dataBaseShops.add(shop);
        return shop;
    }

    public Shop findById(long id) throws ShopNotFoundException {
        for (Shop shop : dataBaseShops) {
            if (id == shop.getId()) {
                return shop;
            }
        }
        throw new ShopNotFoundException("Shop with id: " + id + " not found");
    }

    public void delete(long id) throws ShopNotFoundException {
        dataBaseShops.remove(findById(id));
    }

    public ArrayList<Shop> getShops() {
        return dataBaseShops;
    }

    public Shop update(Shop shopToUpdate) throws ShopNotFoundException {
        for (Shop shop : dataBaseShops) {
            if (shop.getId() == shopToUpdate.getId()) {
                shop = shopToUpdate;
                return shop;
            }
        }
        throw new ShopNotFoundException("Shop with id : " + shopToUpdate.getId() + " not found");
    }
}
