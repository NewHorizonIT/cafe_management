package com.springboot.springboot.service;

import com.springboot.springboot.model.Category;
import com.springboot.springboot.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    // Lấy tất cả danh mục
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    // Lấy danh mục theo ID
    public Category getCategoryById(int id) {
        return categoryRepository.findById(id);
    }

    // Thêm danh mục
    public int createCategory(Category category) {
        return categoryRepository.save(category);
    }

    // Cập nhật danh mục
    public int updateCategory(Category category) {
        return categoryRepository.update(category);
    }

    // Xóa danh mục
    public int deleteCategory(int id) {
        return categoryRepository.delete(id);
    }
}
