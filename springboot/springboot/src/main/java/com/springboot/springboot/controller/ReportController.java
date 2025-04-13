// ReportController
package com.springboot.springboot.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.springboot.model.Material;
import com.springboot.springboot.model.Order;
import com.springboot.springboot.model.Purchase;
import com.springboot.springboot.service.ReportService;

@RestController
@RequestMapping("/api/reports")
public class ReportController {
    @Autowired
    private ReportService reportService;

    // Xem toàn bộ báo cáo bán hàng
    @GetMapping("/orders")
    public List<Order> getAllOrders() {
        return reportService.getAllOrders();
    }

    // Xem toàn bộ báo cáo mua hàng
    @GetMapping("/purchases")
    public List<Purchase> getAllPurchases() {
        return reportService.getAllPurchases();
    }

    // Xem toàn bộ báo cáo nhập kho
    @GetMapping("/material")
    public List<Material> getAllMaterial() {
        return reportService.getAllMaterials();
    }

    // Thống kê tổng hợp
    @GetMapping("/stats")
    public Map<String, Object> getStats() {
        return reportService.getStats();
    }
}
