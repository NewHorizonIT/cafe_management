package com.springboot.springboot.controller;

import com.springboot.springboot.model.Drink;
import com.springboot.springboot.service.DrinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/drinks")
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
    @GetMapping("/category/{categoryId}")
    public List<Drink> getDrinksByCategory(@PathVariable int categoryId) {
        return drinkService.getDrinksByCategory(categoryId);
    }

    // Tạo sản phẩm mới
    @PostMapping
    public String createDrink(@RequestBody Drink drink) {
        int result = drinkService.createDrink(drink);
        return result > 0 ? "Them san pham thanh cong" : "Them san pham that bai";
    }

    // Cập nhật sản phẩm
    @PutMapping("/{id}")
    public String updateDrink(@PathVariable int id, @RequestBody Drink drink) {
        drink.setId(id);
        int result = drinkService.updateDrink(drink);
        return result > 0 ? "Cap nhat san pham thanh cong" : "Cap nhat san pham that bai";
    }

    // Xóa sản phẩm
    @DeleteMapping("/{id}")
    public String deleteDrink(@PathVariable int id) {
        int result = drinkService.deleteDrink(id);
        return result > 0 ? "Xoa san pham thanh cong" : "Xoa san pham that bai";
    }
}
