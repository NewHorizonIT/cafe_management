// DashboardController
package com.springboot.springboot.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.springboot.repository.MaterialRepository;
import com.springboot.springboot.repository.OrderRepository;
import com.springboot.springboot.repository.PurchaseRepository;
import com.springboot.springboot.service.ReportService;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private PurchaseRepository purchaseRepository;
    @Autowired
    private MaterialRepository materialRepository;
    @Autowired
    private ReportService reportService;

    // API dashboard: Tổng quan dữ liệu
    @GetMapping("/summary")
    public Map<String, Object> getDashboardSummary() {
        Map<String, Object> summary = new HashMap<>();
        summary.put("totalOrders", orderRepository.count());
        summary.put("totalPurchases", purchaseRepository.count());
        summary.put("totalMaterial", materialRepository.count());
        return summary;
    }

    // API dashboard: Biểu đồ doanh thu theo ngày
    @GetMapping("/daily-sales")
    public List<Map<String, Object>> getDailySales() {
        return reportService.getDailySales();
    }
}
