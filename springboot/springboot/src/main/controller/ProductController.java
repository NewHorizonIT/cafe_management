package com.springboot.springboot.controller;

import com.springboot.springboot.model.Drink;
import com.springboot.springboot.service.DrinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private DrinkService drinkService;

    @GetMapping("/api/products")
    public List<Drink> getAllProducts() {
        return drinkService.getAllDrinks();
    }

    @GetMapping("/api/products/search")
    public List<Drink> searchProducts(@RequestParam String keyword) {
        return drinkService.searchDrinks(keyword);
    }

    @GetMapping("/api/products/filter")
    public List<Drink> filterProducts(@RequestParam String category,
                                      @RequestParam double minPrice,
                                      @RequestParam double maxPrice) {
        return drinkService.filterDrinks(category, minPrice, maxPrice);
    }
}
