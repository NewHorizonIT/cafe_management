package com.springboot.springboot.controller;

import com.springboot.springboot.entity.Drink;
import com.springboot.springboot.model.DrinkMaterial;
import com.springboot.springboot.service.DrinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/api/v1/drinks")
public class DrinkController {

    @Autowired
    private DrinkService drinkService;

    // Lấy danh sách sản phẩm
    @GetMapping("/all")
    public ResponseEntity<List<Drink>> getAllDrinks() {
        return ResponseEntity.ok(drinkService.getAllDrinks());
    }

    // Tìm kiếm sản phẩm theo tên
    @GetMapping("/search")
    public ResponseEntity<List<Drink>> getDrinksByName(@RequestParam("name") String name) {
        return ResponseEntity.ok(drinkService.getDrinksByName(name));
    }

    // Lấy sản phẩm theo loại (Category ID)
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<Drink>> getDrinksByCategory(@PathVariable int categoryId) {
        return ResponseEntity.ok(drinkService.getDrinksByCategory(categoryId));
    }

    // Xoa sản phẩm
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDrink(@PathVariable int id) {
        if (drinkService.deleteDrink(id) > 0) {
            return ResponseEntity.ok("Xóa sản phẩm thành công");
        } else {
            return ResponseEntity.status(404).body("Không tìm thấy sản phẩm với ID: " + id);
        }
    }

}