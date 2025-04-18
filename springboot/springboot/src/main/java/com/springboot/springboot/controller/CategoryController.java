package com.springboot.springboot.controller;

import com.springboot.springboot.model.Category;
import com.springboot.springboot.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    // Lấy danh sách tất cả danh mục
    @GetMapping
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }

    // Lấy danh mục theo ID
    @GetMapping("/{id}")
    public Category getCategoryById(@PathVariable int id) {
        return categoryService.getCategoryById(id);
    }

    // Thêm danh mục mới
    @PostMapping
    public String createCategory(@RequestBody Category category) {
        int result = categoryService.createCategory(category);
        return result > 0 ? "Them danh muc thanh cong" : "Them danh muc that bai";
    }

    // Cập nhật danh mục
    @PutMapping("/{id}")
    public String updateCategory(@PathVariable int id, @RequestBody Category category) {
        category.setId(id);
        int result = categoryService.updateCategory(category);
        return result > 0 ? "Cap nhat danh muc thanh cong" : "Cap nhat danh muc that bai";
    }

    // Xóa danh mục
    @DeleteMapping("/{id}")
    public String deleteCategory(@PathVariable int id) {
        int result = categoryService.deleteCategory(id);
        return result > 0 ? "Xoa danh muc thanh cong" : "Xoa danh muc that bai";
    }
}
