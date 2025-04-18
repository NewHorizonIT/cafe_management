package com.springboot.springboot.controller;

import com.springboot.springboot.model.Purchase;
import com.springboot.springboot.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/purchases")
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;

    // Lấy danh sách phiếu nhập kho
    @GetMapping
    public List<Purchase> getAllPurchases() {
        return purchaseService.getAllPurchases();
    }

    // Lấy phiếu nhập kho theo ID
    @GetMapping("/{id}")
    public Purchase getPurchaseById(@PathVariable int id) {
        return purchaseService.getPurchaseById(id);
    }

    // Thêm phiếu nhập kho mới
    @PostMapping
    public String createPurchase(@RequestBody Purchase purchase) {
        int result = purchaseService.createPurchase(purchase);
        return result > 0 ? "Them phieu nhap kho thanh cong" : "Them phieu nhap kho that bai";
    }

    // Cập nhật phiếu nhập kho
    @PutMapping("/{id}")
    public String updatePurchase(@PathVariable int id, @RequestBody Purchase purchase) {
        purchase.setPurchaseId(id);
        int result = purchaseService.updatePurchase(purchase);
        return result > 0 ? "Cap nhat phieu nhap kho thanh cong" : "Cap nhat phieu nhap kho that bai";
    }

    // Xóa phiếu nhập kho
    @DeleteMapping("/{id}")
    public String deletePurchase(@PathVariable int id) {
        int result = purchaseService.deletePurchase(id);
        return result > 0 ? "Xoa phieu nhap kho thanh cong" : "Xoa phieu nhap kho that bai";
    }
}
