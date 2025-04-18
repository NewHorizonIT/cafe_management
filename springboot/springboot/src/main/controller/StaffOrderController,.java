package com.springboot.springboot.controller;

import com.springboot.springboot.model.Order;
import com.springboot.springboot.model.OrderDetail;
import com.springboot.springboot.service.StaffOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/staff/orders")
public class StaffOrderController {

    @Autowired
    private StaffOrderService staffOrderService;

    // 1. Duyệt đơn hàng
    @PutMapping("/{orderId}/approve")
    public Order approveOrder(@PathVariable int orderId) {
        return staffOrderService.approveOrder(orderId);
    }

    // 2. Hủy đơn hàng
    @PutMapping("/{orderId}/cancel")
    public Order cancelOrder(@PathVariable int orderId) {
        return staffOrderService.cancelOrder(orderId);
    }

    // 3. In hóa đơn (xem chi tiết)
    @GetMapping("/{orderId}/invoice")
    public List<OrderDetail> printInvoice(@PathVariable int orderId) {
        return staffOrderService.getOrderDetails(orderId);
    }
}
