package com.springboot.springboot.service;

import com.springboot.springboot.entity.Drink;
import com.springboot.springboot.model.DrinkMaterial;
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

    // Lấy sản phẩm theo tên
    public List<Drink> getDrinksByName(String name) {
        return drinkRepository.findByName(name);
    }

    // Lấy sản phẩm theo loại (Category ID)
    public List<Drink> getDrinksByCategory(int categoryId) {
        return drinkRepository.findByCategoryId(categoryId);
    }

    // Kiểm tra ID tồn tại
    public boolean existsById(int id) {
        return drinkRepository.existsById(id);
    }

    // Lấy thumbnail theo ID
    public String getThumbnailById(int id) {
        return drinkRepository.getThumbnailById(id);
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

    // Thêm Material cho Drink
    public int addMaterialToDrink(DrinkMaterial drinkMaterial) {
        return drinkRepository.addMaterialToDrink(drinkMaterial);
    }

    // Lấy danh sách Material cần cho Drink
    public List<DrinkMaterial> getMaterialsForDrink(int drinkId) {
        return drinkRepository.getMaterialsForDrink(drinkId);
    }

    // Xóa tất cả Material liên kết với Drink
    public int deleteMaterialsForDrink(int drinkId) {
        return drinkRepository.deleteMaterialsForDrink(drinkId);
    }
}