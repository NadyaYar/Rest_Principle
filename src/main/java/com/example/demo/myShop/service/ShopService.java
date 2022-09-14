package com.example.demo.myShop.service;

import com.example.demo.myShop.dto.ShopDto;
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

    public ShopDto createShopDto(ShopDto shopDto) {
        Shop shop = convertToEntity(shopDto);
        shopRepository.save(shop);
        return shopDto;
    }

    public Shop convertToEntity(ShopDto shopDto) {
        Shop shop = new Shop();
        shop.setId(shopDto.getId());
        shop.setName(shopDto.getName());
        shop.setCity(shopDto.getCity());
        shop.setIsHasSite(shopDto.getIsHasSite());
        return shop;
    }

    public Shop findById(long id) throws ShopNotFoundException {
        isExists(id);
        return shopRepository.findById(id).orElse(null);
    }

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
        shop.setIsHasSite(shopToUpdate.getIsHasSite());

        return shop;
    }

    private void isExists(long id) throws ShopNotFoundException {
        boolean isExists = shopRepository.existsById(id);
        if (!isExists) {
            throw new ShopNotFoundException("Shop with id: " + id + " not found");
        }
    }
}
