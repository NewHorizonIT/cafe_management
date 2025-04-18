package com.springboot.springboot.service;

import com.springboot.springboot.model.Drink;
import com.springboot.springboot.repository.DrinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DrinkService {

    @Autowired
    private DrinkRepository drinkRepository;

    public List<Drink> getAllDrinks() {
        return drinkRepository.findAll();
    }

    public List<Drink> searchDrinks(String keyword) {
        return drinkRepository.findByNameContaining(keyword);
    }

    public List<Drink> filterDrinks(String category, double minPrice, double maxPrice) {
        return drinkRepository.findByCategoryAndPriceBetween(category, minPrice, maxPrice);
    }
}
