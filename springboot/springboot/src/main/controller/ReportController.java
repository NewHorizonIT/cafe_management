package com.springboot.springboot.controller;

import com.springboot.springboot.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Map;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    @Autowired
    private ReportService reportService;

    // 1. Tổng doanh thu
    @GetMapping("/total-revenue")
    public double getTotalRevenue() {
        return reportService.getTotalRevenue();
    }

    // 2. Doanh thu theo ngày
    @GetMapping("/revenue/day")
    public double getRevenueByDay(@RequestParam String date) {
        return reportService.getRevenueByDay(LocalDate.parse(date));
    }

    // 3. Doanh thu theo tháng
    @GetMapping("/revenue/month")
    public double getRevenueByMonth(@RequestParam int month, @RequestParam int year) {
        return reportService.getRevenueByMonth(month, year);
    }

    // 4. Thống kê đơn hàng
    @GetMapping("/orders-summary")
    public Map<String, Integer> getOrderSummary() {
        return reportService.getOrderSummary();
    }

    // 5. Top sản phẩm bán chạy
    @GetMapping("/top-products")
    public Map<String, Integer> getTopSellingProducts() {
        return reportService.getTopSellingProducts();
    }
}
