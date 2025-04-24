package com.springboot.springboot.service;

import com.springboot.springboot.model.Order;
import com.springboot.springboot.model.OrderDetail;
import com.springboot.springboot.repository.OrderRepository;
import com.springboot.springboot.repository.OrderDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StaffOrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    // Duyệt đơn (status = 1)
    public Order approveOrder(int orderId) {
        Order order = orderRepository.findById(orderId);
        if (order == null) {
            throw new RuntimeException("Order not found");
        }

        order.setStatus(1);
        return orderRepository.save(order);
    }

    // Hủy đơn (status = -1)
    public Order cancelOrder(int orderId) {
        Order order = orderRepository.findById(orderId);

        if (order == null) {
            throw new RuntimeException("Order not found");
        }

        order.setStatus(-1); // -1 = hủy
        return orderRepository.save(order);
    }

    // In hóa đơn (lấy chi tiết đơn hàng)
    public List<OrderDetail> getOrderDetails(int orderId) {
        return orderDetailRepository.findByOrderId(orderId);
    }
}
