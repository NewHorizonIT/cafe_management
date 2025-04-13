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
    private MaterialRepository inventoryRepository;

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public List<Purchase> getAllPurchases() {
        return purchaseRepository.findAll();
    }

    public List<Material> getAllMaterial() {
        return inventoryRepository.findAll();
    }
  
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
}
