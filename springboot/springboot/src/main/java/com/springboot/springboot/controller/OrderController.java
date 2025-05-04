package com.springboot.springboot.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.springboot.dto.request.OrderRequest;
import com.springboot.springboot.dto.response.OrderResponse;
import com.springboot.springboot.entity.Order;
import com.springboot.springboot.service.OrderService;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    // Tạo đơn hàng mới
    @PostMapping
    public OrderResponse createOrder(@RequestBody OrderRequest orderRequest) {
        // Tạo một đối tượng Order từ request
        OrderResponse orderResponse = orderService.createOrder(orderRequest);

        // Trả về thông tin đơn hàng đã lưu
        return orderResponse;
    }

    // Lấy thông tin đơn hàng theo ID
    @GetMapping("/{id}")
    public OrderResponse getOrderById(@PathVariable int id) {
        // Lấy thông tin đơn hàng từ cơ sở dữ liệu
        OrderResponse orderResponse = orderService.getOrderById(id);

        // Trả về thông tin đơn hàng
        return orderResponse;
    }

    // Lấy tất cả đơn hàng
    @GetMapping
    public List<OrderResponse> getAllOrders() {
        // Lấy danh sách đơn hàng từ cơ sở dữ liệu
        List<OrderResponse> orderResponses = orderService.getAllOrders();

        // Trả về danh sách đơn hàng
        return orderResponses;
    }

    // Lấy tất cả đơn hàng của người dùng
    @GetMapping("/user/{userId}")
    public List<OrderResponse> getOrdersByUserId(@PathVariable int userId) {
        // Lấy danh sách đơn hàng từ cơ sở dữ liệu
        List<OrderResponse> orderResponses = orderService.getOrdersByUserId(userId);

        // Trả về danh sách đơn hàng
        return orderResponses;
    }

    // Lấy tất cả đơn hàng theo trạng thái
    @GetMapping("/status/{status}")
    public List<OrderResponse> getOrdersByStatus(@PathVariable String status) {
        // Lấy danh sách đơn hàng từ cơ sở dữ liệu
        List<OrderResponse> orderResponses = orderService.getOrdersByStatus(status);

        // Trả về danh sách đơn hàng
        return orderResponses;
    }

    // Cập nhật trạng thái đơn hàng
    @PostMapping("/{id}/{status}")
    public OrderResponse updateOrderStatus(@PathVariable int id, @PathVariable String status) {
        // Cập nhật trạng thái đơn hàng trong cơ sở dữ liệu
        OrderResponse orderResponse = orderService.updateOrderStatus(id, status);

        // Trả về thông tin đơn hàng đã cập nhật
        return orderResponse;
    }

}
