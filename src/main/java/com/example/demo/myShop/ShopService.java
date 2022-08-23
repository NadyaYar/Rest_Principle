package com.example.demo.myShop;

import com.example.demo.myShop.exception.BadRequestException;
import com.example.demo.myShop.exception.UserNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ShopService {

    private final ArrayList<Shop> dataBaseShops = new ArrayList<>();

    public Shop createShop(Shop shop) throws BadRequestException {
            if (!dataBaseShops.contains(shop)) {
                dataBaseShops.add(shop);
                return shop;
            }
        throw new BadRequestException(" User with id: " + shop.getId() + "already exist");
    }

    public Shop findById(long id) throws UserNotFoundException {
        for (Shop shop : dataBaseShops) {
            if ((id == shop.getId())) {
                return shop;
            }
        }
        throw new UserNotFoundException("User with id " + id + " no found");
    }

    public void delete(long id) throws UserNotFoundException {
        dataBaseShops.remove(findById(id));
    }

    public ArrayList<Shop> getShops() {
        return dataBaseShops;
    }

    public Shop update(Shop shop) throws UserNotFoundException {
        for (Shop shop1 : dataBaseShops) {
            if (shop1 == findById(shop.getId())) {
                shop1 = shop;
                return shop1;
            }
        }
        throw new UserNotFoundException(" User with id: " + shop.getId() + " no found");
    }
}
