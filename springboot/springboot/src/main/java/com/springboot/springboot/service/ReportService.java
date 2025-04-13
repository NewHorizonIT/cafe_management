// ReportService
package com.springboot.springboot.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.springboot.model.Material;
import com.springboot.springboot.model.Order;
import com.springboot.springboot.model.Purchase;
import com.springboot.springboot.repository.MaterialRepository;
import com.springboot.springboot.repository.OrderRepository;
import com.springboot.springboot.repository.PurchaseRepository;

@Service
public class ReportService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private PurchaseRepository purchaseRepository;
    @Autowired
    private MaterialRepository materialRepository;

    // Lấy tất cả đơn hàng
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    // Lấy tất cả đơn nhập hàng
    public List<Purchase> getAllPurchases() {
        return purchaseRepository.findAll();
    }

    // Lấy tất cả tồn kho
    public List<Material> getAllMaterials() {
        return materialRepository.findAll();
    }

    // Doanh thu theo ngày cho biểu đồ
    public List<Map<String, Object>> getDailySales() {
        List<Order> orders = orderRepository.findAll();

        return orders.stream()
            .filter(order -> order.getCreatedAt() != null)
            .collect(Collectors.groupingBy(
                order -> order.getCreatedAt().toLocalDate().toString(),
                Collectors.summingInt(Order::getTotal)
            ))
            .entrySet().stream()
            .map(entry -> {
                Map<String, Object> map = new HashMap<>();
                map.put("date", entry.getKey());
                map.put("sales", entry.getValue());
                return map;
            })
            .collect(Collectors.toList());
    }

    // Thống kê tổng hợp
    public Map<String, Object> getStats() {
        Map<String, Object> stats = new HashMap<>();

        // Thống kê bán hàng
        List<Order> orders = orderRepository.findAll();
        int totalSales = orders.stream().mapToInt(Order::getTotal).sum();
        stats.put("totalSales", totalSales);
        stats.put("orderCount", orders.size());

        // Thống kê mua hàng
        List<Purchase> purchases = purchaseRepository.findAll();
        int totalPurchaseCost = purchases.stream().mapToInt(Purchase::getTotal).sum();
        stats.put("totalPurchaseCost", totalPurchaseCost);
        stats.put("purchaseCount", purchases.size());

        // Thống kê tồn kho
        List<Material> materials = materialRepository.findAll();
        Map<String, Integer> inventoryByMaterial = materials.stream()
            .collect(Collectors.toMap(
                Material::getName,
                Material::getQuantity,
                Integer::sum
            ));
        stats.put("inventoryByMaterial", inventoryByMaterial);
        stats.put("inventoryItemCount", materials.size());

        return stats;
    }
}
