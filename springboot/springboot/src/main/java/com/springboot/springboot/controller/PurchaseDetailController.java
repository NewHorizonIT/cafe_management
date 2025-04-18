package com.springboot.springboot.controller;

import com.springboot.springboot.model.PurchaseDetail;
import com.springboot.springboot.service.PurchaseDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/purchase-details")
public class PurchaseDetailController {

    @Autowired
    private PurchaseDetailService purchaseDetailService;

    // Lấy toàn bộ chi tiết phiếu nhập
    @GetMapping
    public List<PurchaseDetail> getAllDetails() {
        return purchaseDetailService.getAllPurchaseDetails();
    }

    // Lấy chi tiết theo mã phiếu nhập
    @GetMapping("/{purchaseId}")
    public List<PurchaseDetail> getByPurchaseId(@PathVariable int purchaseId) {
        return purchaseDetailService.getDetailsByPurchaseId(purchaseId);
    }

    // Thêm mới chi tiết phiếu nhập
    @PostMapping
    public String addDetail(@RequestBody PurchaseDetail detail) {
        int result = purchaseDetailService.createPurchaseDetail(detail);
        return result > 0 ? "Them chi tiet thanh cong" : "Them chi tiet that bai";
    }

    // Cập nhật chi tiết phiếu nhập
    @PutMapping
    public String updateDetail(@RequestBody PurchaseDetail detail) {
        int result = purchaseDetailService.updatePurchaseDetail(detail);
        return result > 0 ? "Cap nhat chi tiet thanh cong" : "Cap nhat chi tiet that bai";
    }

    // Xóa chi tiết theo materialId và purchaseId
    @DeleteMapping
    public String deleteDetail(@RequestParam int materialId, @RequestParam int purchaseId) {
        int result = purchaseDetailService.deletePurchaseDetail(materialId, purchaseId);
        return result > 0 ? "Xoa chi tiet thanh cong" : "Xoa chi tiet that bai";
    }
}
