package com.springboot.springboot.controller;

import com.springboot.springboot.entity.Drink;
import com.springboot.springboot.model.DrinkMaterial;
import com.springboot.springboot.service.DrinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
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

    // Xóa sản phẩm
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDrink(@PathVariable int id) {
        if (drinkService.deleteDrink(id) > 0) {
            return ResponseEntity.ok("Xóa sản phẩm thành công");
        } else {
            return ResponseEntity.status(404).body("Không tìm thấy sản phẩm với ID: " + id);
        }
    }

    // Thêm sản phẩm
    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<String> addDrink(
            @RequestPart("drink") Drink drink,
            @RequestPart(value = "thumbnail", required = false) MultipartFile thumbnail) {
        try {
            // Lưu file thumbnail nếu có
            if (thumbnail != null && !thumbnail.isEmpty()) {
                String uploadDir = "./images/";
                Path uploadPath = Paths.get(uploadDir);
                if (!Files.exists(uploadPath)) {
                    Files.createDirectories(uploadPath);
                }
                String fileName = System.currentTimeMillis() + "_" + thumbnail.getOriginalFilename();
                Path filePath = uploadPath.resolve(fileName);
                Files.write(filePath, thumbnail.getBytes());
                drink.setThumbnail("/images/" + fileName);
            }

            // Thiết lập thời gian tạo, cập nhật và status
            drink.setCreatedAt(LocalDateTime.now());
            drink.setUpdatedAt(LocalDateTime.now());
            drink.setStatus("ACTIVE"); // Thêm status mặc định để tránh lỗi NOT NULL

            // Lưu drink vào database
            drinkService.saveDrink(drink);
            return ResponseEntity.ok("Thêm sản phẩm thành công");
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Lỗi khi lưu file: " + e.getMessage());
        }
    }
}