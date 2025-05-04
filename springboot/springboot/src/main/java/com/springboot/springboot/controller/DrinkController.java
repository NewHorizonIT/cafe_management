package com.springboot.springboot.controller;

import com.springboot.springboot.entity.Drink;
import com.springboot.springboot.service.DrinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/drinks")
public class DrinkController {

    @Autowired
    private DrinkService drinkService;

    // Lấy danh sách sản phẩm
    @GetMapping
    public List<Drink> getAllDrinks() {
        return drinkService.getAllDrinks();
    }

    // Lấy sản phẩm theo ID
    @GetMapping("/{id}")
    public Drink getDrinkById(@PathVariable int id) {
        return drinkService.getDrinkById(id);
    }

    // Lấy sản phẩm theo loại (Category ID)

}
