package com.springboot.springboot.service;

import com.springboot.springboot.model.Order;
import com.springboot.springboot.model.OrderDetail;
import com.springboot.springboot.model.Drink;
import com.springboot.springboot.repository.OrderRepository;
import com.springboot.springboot.repository.OrderDetailRepository;
import com.springboot.springboot.repository.DrinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ReportService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private DrinkRepository drinkRepository;

    public double getTotalRevenue() {
        return orderRepository.findAll().stream()
                .filter(o -> o.getStatus() == 1)
                .mapToDouble(Order::getTotal)
                .sum();
    }

    public double getRevenueByDay(LocalDate date) {
        return orderRepository.findAll().stream()
                .filter(o -> o.getStatus() == 1 && o.getCreatedAt().toLocalDate().equals(date))
                .mapToDouble(Order::getTotal)
                .sum();
    }

    public double getRevenueByMonth(int month, int year) {
        return orderRepository.findAll().stream()
                .filter(o -> o.getStatus() == 1 &&
                        o.getCreatedAt().getMonthValue() == month &&
                        o.getCreatedAt().getYear() == year)
                .mapToDouble(Order::getTotal)
                .sum();
    }

    public Map<String, Integer> getOrderSummary() {
        List<Order> orders = orderRepository.findAll();
        int approved = (int) orders.stream().filter(o -> o.getStatus() == 1).count();
        int cancelled = (int) orders.stream().filter(o -> o.getStatus() == -1).count();
        int pending = (int) orders.stream().filter(o -> o.getStatus() == 0).count();

        Map<String, Integer> summary = new HashMap<>();
        summary.put("approved", approved);
        summary.put("cancelled", cancelled);
        summary.put("pending", pending);
        return summary;
    }

    public Map<String, Integer> getTopSellingProducts() {
        List<OrderDetail> details = orderDetailRepository.findAll();
        Map<Integer, Integer> productSales = new HashMap<>();

        for (OrderDetail detail : details) {
            int drinkId = detail.getDrinkId();
            int quantity = detail.getQuantity();
            productSales.put(drinkId, productSales.getOrDefault(drinkId, 0) + quantity);
        }

        // Lấy tên sản phẩm
        Map<String, Integer> top = new HashMap<>();
        productSales.entrySet().stream()
            .sorted((a, b) -> b.getValue() - a.getValue())
            .limit(5)
            .forEach(entry -> {
                String name = drinkRepository.findById(entry.getKey()).map(Drink::getName).orElse("Unknown");
                top.put(name, entry.getValue());
            });

        return top;
    }
}
