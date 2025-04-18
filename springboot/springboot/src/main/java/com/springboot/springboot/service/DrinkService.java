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

    // Lấy danh sách sản phẩm
    public List<Drink> getAllDrinks() {
        return drinkRepository.findAll();
    }

    // Lấy sản phẩm theo ID
    public Drink getDrinkById(int id) {
        return drinkRepository.findById(id);
    }

    // Lấy sản phẩm theo loại (Category ID)
    public List<Drink> getDrinksByCategory(int categoryId) {
        return drinkRepository.findByCategoryId(categoryId);
    }

    // Tạo sản phẩm mới
    public int createDrink(Drink drink) {
        return drinkRepository.save(drink);
    }

    // Cập nhật sản phẩm
    public int updateDrink(Drink drink) {
        return drinkRepository.update(drink);
    }

    // Xóa sản phẩm
    public int deleteDrink(int id) {
        return drinkRepository.delete(id);
    }
}
