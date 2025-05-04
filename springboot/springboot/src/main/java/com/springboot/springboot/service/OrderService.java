package com.springboot.springboot.service;

import com.springboot.springboot.dto.request.OrderDetailRequest;
import com.springboot.springboot.dto.request.OrderRequest;
import com.springboot.springboot.dto.response.OrderDetailResponse;
import com.springboot.springboot.dto.response.OrderResponse;
import com.springboot.springboot.entity.Drink;
import com.springboot.springboot.entity.Order;
import com.springboot.springboot.entity.OrderDetail;
import com.springboot.springboot.repository.OrderRepository;
import com.springboot.springboot.repository.DrinkRepository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private DrinkRepository drinkRepository;

    public OrderResponse createOrder(OrderRequest orderRequest) {
        // Tạo một đối tượng Order từ request
        Order order = new Order();
        order.setUserId(orderRequest.getUserId());
        order.setStatus(orderRequest.getStatus());
        order.setCreatedAt(LocalDateTime.now());
        order.setTotal(0);
        // Lưu đơn hàng vào cơ sở dữ liệu
        Order savedOrder = orderRepository.save(order);

        int total = 0;
        for (OrderDetail orderDetail : orderRequest.getOrderDetails()) {
            orderDetail.setOrderId(savedOrder.getId());
            Drink drink = drinkRepository.findById(orderDetail.getDrinkId());
            if (drink == null) {
                throw new RuntimeException("Drink not found with id: " + orderDetail.getDrinkId());
            }
            int subTotal = drink.getPrice() * orderDetail.getQuantity();
            total += subTotal;

            OrderDetailRequest orderDetailRequest = new OrderDetailRequest();
            orderDetailRequest.setDrinkId(orderDetail.getDrinkId());
            orderDetailRequest.setOrderId(savedOrder.getId());
            orderDetailRequest.setQuantity(orderDetail.getQuantity());
            orderDetailRequest.setPrice(drink.getPrice());
            orderDetailRequest.setTotal(subTotal);

            // Lưu chi tiết đơn hàng vào cơ sở dữ liệu
            orderRepository.saveOrderDetail(orderDetailRequest);
        }

        // Cập nhật tổng tiền của đơn hàng
        savedOrder.setTotal(total);

        // Cap nhật tổng tiền của đơn hàng trong cơ sở dữ liệu
        orderRepository.updateTotal(savedOrder.getId(), total);

        // Trả về thông tin đơn hàng đã lưu
        return OrderResponse.builder()
                .id(savedOrder.getId())
                .userId(savedOrder.getUserId())
                .total(savedOrder.getTotal())
                .status(savedOrder.getStatus())
                .createdAt(savedOrder.getCreatedAt().toString())
                .build();

    }

    public OrderResponse getOrderById(int id) {
        // Lấy thông tin đơn hàng từ cơ sở dữ liệu
        OrderResponse order = orderRepository.findById(id);
        if (order == null) {
            throw new RuntimeException("Order not found with id: " + id);
        }

        // Lay thông tin chi tiết đơn hàng
        List<OrderDetailResponse> orderDetails = orderRepository.getOrderDetailsById(id);

        // Chuyển đổi Order thành OrderResponse
        return OrderResponse.builder()
                .id(order.getId())
                .userId(order.getUserId())
                .orderDetails(orderDetails)
                .total(order.getTotal())
                .status(order.getStatus())
                .createdAt(order.getCreatedAt().toString())
                .build();
    }

    // Lấy tất cả dơn hàng
    public List<OrderResponse> getAllOrders() {
        return orderRepository.findAll();
    }

    // Lấy tất cả đơn hàng theo userId
    public List<OrderResponse> getOrdersByUserId(int userId) {
        return orderRepository.findAllByUserId(userId);
    }

    // Lấy tất cả đơn hàng theo status
    public List<OrderResponse> getOrdersByStatus(String status) {
        return orderRepository.findAllByStatus(status);
    }

    // Cập nhật trạng thái đơn hàng
    public OrderResponse updateOrderStatus(int orderId, String status) {
        OrderResponse orderResponse = orderRepository.updateStatus(orderId, status);

        return OrderResponse.builder()
                .id(orderResponse.getId())
                .userId(orderResponse.getUserId())
                .total(orderResponse.getTotal())
                .status(orderResponse.getStatus())
                .createdAt(orderResponse.getCreatedAt())
                .build();
    }

}