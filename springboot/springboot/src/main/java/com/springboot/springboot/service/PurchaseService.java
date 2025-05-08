package com.springboot.springboot.service;

import com.springboot.springboot.model.Purchase;
import com.springboot.springboot.repository.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PurchaseService {

    @Autowired
    private PurchaseRepository purchaseRepository;

    // Lấy danh sách phiếu nhập kho
    public List<Purchase> getAllPurchases() {
        return purchaseRepository.findAll();
    }

    // Lấy phiếu nhập kho theo ID
    public Purchase getPurchaseById(int id) {
        return purchaseRepository.findById(id);
    }

    // Thêm phiếu nhập kho mới
    public int createPurchase(Purchase purchase) {
        return purchaseRepository.save(purchase);
    }

    // Cập nhật phiếu nhập kho
    public int updatePurchase(Purchase purchase) {
        return purchaseRepository.update(purchase);
    }

    // Xóa phiếu nhập kho
    public int deletePurchase(int id) {
        return purchaseRepository.delete(id);
    }

    // Tìm kiếm phiếu nhập kho theo ID nhà cung cấp
    public List<Purchase> findPurchasesBySupplierId(int supplierId) {
        return purchaseRepository.findBySupplierId(supplierId);
    }
}
