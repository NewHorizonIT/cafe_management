package com.springboot.springboot.service;

import com.springboot.springboot.entity.Drink;
import com.springboot.springboot.repository.DrinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DrinkService {

    @Autowired
    private DrinkRepository drinkRepository;

    // Lưu thông tin sản phẩm
    public void saveDrink(Drink drink) {
        drinkRepository.save(drink);
    }

    // Lấy danh sách sản phẩm
    public List<Drink> getAllDrinks() {
        return drinkRepository.findAll();
    }

    // Lấy thông tin sản phẩm theo id
    public Drink getDrinkById(int id) {
        return drinkRepository.findById(id);
    }

}
