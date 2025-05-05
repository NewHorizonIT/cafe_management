package com.springboot.springboot.service;

import com.springboot.springboot.entity.User;
import com.springboot.springboot.model.Drink;
import com.springboot.springboot.repository.OrderRepository;
import com.springboot.springboot.repository.UserRepository;
import com.springboot.springboot.repository.DrinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DashboardService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DrinkRepository drinkRepository;

    // Lọc doanh thu theo ngày và trả về danh sách chuỗi
    public List<String> getRevenueByDate(LocalDate startDate, LocalDate endDate) {
        List<Map<String, Object>> revenueData = orderRepository.findRevenueByDateRange(startDate, endDate);
        return revenueData.stream()
                .map(data -> {
                    LocalDate date = (LocalDate) data.get("date");
                    BigDecimal revenue = (BigDecimal) data.get("revenue");
                    return String.format("ngày %s: %s", date.toString(), revenue.toString());
                })
                .collect(Collectors.toList());
    }

    // Thống kê người dùng có chi tiêu cao nhất
    public User getTopUserByRevenue() {
        Long topUserId = orderRepository.findTopUserByRevenue();
        return userRepository.findById(topUserId.intValue()).orElse(null);
    }

    // Lấy 5 sản phẩm bán chạy nhất
    public List<Drink> getTopDrinks() {
        List<Long> topDrinkIds = orderRepository.findTopDrinks(5);
        return drinkRepository.findByIds(topDrinkIds);
    }
}
