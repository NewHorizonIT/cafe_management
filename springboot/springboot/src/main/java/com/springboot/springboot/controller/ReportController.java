// ReportController
package com.springboot.springboot.controller;

import com.springboot.springboot.model.Material;
import com.springboot.springboot.model.Order;
import com.springboot.springboot.model.Purchase;
import com.springboot.springboot.service.ReportService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/reports")
public class ReportController {
    @Autowired
    private ReportService reportService;

    // API thống kê tổng hợp: Bán hàng
    @GetMapping("/orders")
    public List<Order> getAllOrders() {
        return reportService.getAllOrders();
    }

    // API thống kê tổng hợp: Mua hàng
    @GetMapping("/purchases")
    public List<Purchase> getAllPurchases() {
        return reportService.getAllPurchases();
    }

    // API thống kê tổng hợp: Nhập kho
    @GetMapping("/material")
    public List<Material> getAllMaterial() {
        return reportService.getAllMaterial();
    }
}
