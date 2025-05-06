package com.springboot.springboot.service;

import com.springboot.springboot.entity.User;
import com.springboot.springboot.model.Drink;
import com.springboot.springboot.model.Order;
import com.springboot.springboot.model.OrderDetail;
import com.springboot.springboot.repository.OrderRepository;
import com.springboot.springboot.repository.UserRepository;
import com.springboot.springboot.repository.DrinkRepository;
import com.springboot.springboot.repository.OrderDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class DashboardService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DrinkRepository drinkRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    // Lọc doanh thu theo ngày và trả về danh sách chuỗi
    public List<String> getRevenueByDate(LocalDate startDate, LocalDate endDate) {
        List<Order> orders = (List<Order>) orderRepository.findAll();
        Map<LocalDate, Double> revenueByDate = orders.stream()
                .filter(o -> o.getCreatedAt() != null && !o.getCreatedAt().toLocalDate().isBefore(startDate) && !o.getCreatedAt().toLocalDate().isAfter(endDate))
                .collect(Collectors.groupingBy(
                        order -> order.getCreatedAt().toLocalDate(),
                        Collectors.summingDouble(Order::getTotal)
                ));

        return revenueByDate.entrySet().stream()
                .map(entry -> String.format("ngày %s: %.2f", entry.getKey().toString(), entry.getValue()))
                .collect(Collectors.toList());
    }

    // Thống kê người dùng có chi tiêu cao nhất
    public User getTopUserByRevenue() {
        List<Order> orders = (List<Order>) orderRepository.findAll();
        Map<Integer, Double> userRevenue = orders.stream()
                .collect(Collectors.groupingBy(
                        Order::getUserId,
                        Collectors.summingDouble(Order::getTotal)
                ));

        Optional<Map.Entry<Integer, Double>> maxRevenueUser = userRevenue.entrySet().stream()
                .max(Map.Entry.comparingByValue());
        return maxRevenueUser.map(entry -> userRepository.findById(entry.getKey()).orElse(null)).orElse(null);
    }

    // Lấy 5 sản phẩm bán chạy nhất
    public List<Drink> getTopDrinks() {
        List<OrderDetail> orderDetails = orderDetailRepository.findAll();
        Map<Integer, Integer> productSales = orderDetails.stream()
                .collect(Collectors.groupingBy(
                        OrderDetail::getDrinkId,
                        Collectors.summingInt(OrderDetail::getQuantity)
                ));

        List<Integer> topDrinkIds = productSales.entrySet().stream()
                .sorted(Map.Entry.<Integer, Integer>comparingByValue().reversed())
                .limit(5)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        List<Drink> drinks = (List<Drink>) drinkRepository.findAll();
        return drinks.stream()
                .filter(d -> topDrinkIds.contains(d.getId()))
                .collect(Collectors.toList());
    }
}
