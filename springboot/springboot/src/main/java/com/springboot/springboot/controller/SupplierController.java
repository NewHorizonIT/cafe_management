package com.springboot.springboot.controller;

import com.springboot.springboot.model.Supplier;
import com.springboot.springboot.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/suppliers")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    // Lấy danh sách nhà cung cấp
    @GetMapping
    public List<Supplier> getAllSuppliers() {
        return supplierService.getAllSuppliers();
    }

    // Lấy nhà cung cấp theo ID
    @GetMapping("/{id}")
    public Supplier getSupplierById(@PathVariable int id) {
        return supplierService.getSupplierById(id);
    }

    // Thêm nhà cung cấp mới
    @PostMapping
    public String createSupplier(@RequestBody Supplier supplier) {
        int result = supplierService.createSupplier(supplier);
        return result > 0 ? "Them nha cung cap thanh cong" : "Them nha cung cap that bai";
    }

    // Cập nhật nhà cung cấp
    @PutMapping("/{id}")
    public String updateSupplier(@PathVariable int id, @RequestBody Supplier supplier) {
        supplier.setId(id);
        int result = supplierService.updateSupplier(supplier);
        return result > 0 ? "Cap nhat nha cung cap thanh cong" : "Cap nhat nha cung cap that bai";
    }

    // Xóa nhà cung cấp
    @DeleteMapping("/{id}")
    public String deleteSupplier(@PathVariable int id) {
        int result = supplierService.deleteSupplier(id);
        return result > 0 ? "Xoa nha cung cap thanh cong" : "Xoa nha cung cap that bai";
    }
}
