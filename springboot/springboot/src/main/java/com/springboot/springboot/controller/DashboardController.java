package com.springboot.springboot.controller;

import com.springboot.springboot.dto.response.ApiResponse;
import com.springboot.springboot.entity.User;
import com.springboot.springboot.model.Drink;
import com.springboot.springboot.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @Autowired
    private DashboardService dashboardService;

    // API dashboard: Tổng quan dữ liệu
    @GetMapping("/summary")
    public Map<String, Object> getDashboardSummary() {
        Map<String, Object> summary = new HashMap<>();
        summary.put("totalOrders", orderRepository.count());
        summary.put("totalPurchases", purchaseRepository.count());
        summary.put("totalMaterial", materialRepository.count());
        return summary;
    }

    // API dashboard: Doanh thu theo ngày cho biểu đồ
    @GetMapping("/daily-sales")
    public List<Map<String, Object>> getDailySales() {
        return reportService.getDailySales();
    }

    // API lọc doanh thu theo ngày
    @GetMapping("/revenue-by-date")
    public ApiResponse<List<String>> getRevenueByDate(
            @RequestParam("start") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("end") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        List<String> revenueByDate = dashboardService.getRevenueByDate(startDate, endDate);
        return ApiResponse.<List<String>>builder()
                .code("200")
                .message("Success")
                .result(revenueByDate)
                .build();
    }

    // API thống kê người dùng có chi tiêu cao nhất
    @GetMapping("/top-user")
    public ApiResponse<User> getTopUserByRevenue() {
        User topUser = dashboardService.getTopUserByRevenue();
        return ApiResponse.<User>builder()
                .code("200")
                .message("Success")
                .result(topUser)
                .build();
    }

    // API lấy 5 sản phẩm bán chạy nhất
    @GetMapping("/top-drinks")
    public ApiResponse<List<Drink>> getTopDrinks() {
        List<Drink> topDrinks = dashboardService.getTopDrinks();
        return ApiResponse.<List<Drink>>builder()
                .code("200")
                .message("Success")
                .result(topDrinks)
                .build();
    }
}
