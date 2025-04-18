package com.springboot.springboot.controller;

import com.springboot.springboot.model.Material;
import com.springboot.springboot.service.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/materials")
public class MaterialController {

    @Autowired
    private MaterialService materialService;

    // Lấy danh sách nguyên liệu
    @GetMapping
    public List<Material> getAllMaterials() {
        return materialService.getAllMaterials();
    }

    // Lấy nguyên liệu theo ID
    @GetMapping("/{id}")
    public Material getMaterialById(@PathVariable int id) {
        return materialService.getMaterialById(id);
    }

    // Thêm nguyên liệu mới
    @PostMapping
    public String createMaterial(@RequestBody Material material) {
        int result = materialService.createMaterial(material);
        return result > 0 ? "Them nguyen lieu thanh cong" : "Them nguyen lieu that bai";
    }

    // Cập nhật nguyên liệu
    @PutMapping("/{id}")
    public String updateMaterial(@PathVariable int id, @RequestBody Material material) {
        material.setId(id);
        int result = materialService.updateMaterial(material);
        return result > 0 ? "Cap nhat nguyen lieu thanh cong" : "Cap nhat nguyen lieu that bai";
    }

    // Xóa nguyên liệu
    @DeleteMapping("/{id}")
    public String deleteMaterial(@PathVariable int id) {
        int result = materialService.deleteMaterial(id);
        return result > 0 ? "Xoa nguyen lieu thanh cong" : "Xoa nguyen lieu that bai";
    }
}
