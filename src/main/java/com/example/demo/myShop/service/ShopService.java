package com.example.demo.myShop.service;

import com.example.demo.myShop.exception.ShopExistException;
import com.example.demo.myShop.exception.ShopNotFoundException;
import com.example.demo.myShop.model.Shop;
import com.example.demo.myShop.repository.ShopRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShopService {

    private final ShopRepository shopRepository;

    public Shop createShop(Shop shop) throws ShopExistException {
        if (shopRepository.existsById(shop.getId())) {
            throw new ShopExistException("Shop with id: " + shop.getId() + " already exist");
        }
       return shopRepository.save(shop);
    }

    public Shop findById(long id) throws ShopNotFoundException {
        isExists(id);
        return shopRepository.findById(id).orElse(null);
    }

//    @PostConstruct
//    void init() {
//        Shop shop1 = new Shop("Dolyna", "Prospekt Svobody", "Dynastia", 50, true);
//        Shop shop2 = new Shop("Lviv", "Lukasha", "Politech", 5000, true);
//        Shop shop3 = new Shop("Stryj", "Drohobycka", "Kavoman", 20, false);
//        Shop shop4 = new Shop("Kyiv", "Prospekt Nezaleznosti", "Svoboda", 47, false);
//        Shop shop5 = new Shop("Odessa", "Naberezna", "Arkadia", 700, true);
//        shopRepository.saveAll(Arrays.asList(shop1, shop5, shop2, shop4, shop3));
//    }

    public void delete(long id) throws ShopNotFoundException {
        isExists(id);
        shopRepository.deleteById(id);
    }

    public Iterable<Shop> getShops() {
        return shopRepository.findAll();
    }

    public Shop update(Shop shopToUpdate, Long id) throws ShopNotFoundException {
        isExists(shopToUpdate.getId());
        Shop shop = shopRepository.findById(id).orElse(null);

        assert shop != null;
        shop.setCity(shopToUpdate.getCity());
        shop.setStreet(shopToUpdate.getStreet());
        shop.setName(shopToUpdate.getName());
        shop.setNumberOfStaff(shopToUpdate.getNumberOfStaff());
        shop.setHasSite(shopToUpdate.isHasSite());

        return shop;
    }

    public void isExists(long id) throws ShopNotFoundException {
        boolean isExists = shopRepository.existsById(id);
        if (!isExists) {
            throw new ShopNotFoundException("Shop with id: " + id + " not found");
        }
    }
}
