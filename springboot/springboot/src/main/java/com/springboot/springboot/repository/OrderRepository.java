package com.springboot.springboot.repository;

import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.springboot.springboot.dto.request.OrderDetailRequest;
import com.springboot.springboot.dto.response.OrderDetailResponse;
import com.springboot.springboot.dto.response.OrderResponse;
import com.springboot.springboot.entity.Order;
import com.springboot.springboot.entity.OrderDetail;

@Repository
public class OrderRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Lưu đơn hàng vào cơ sở dữ liệu
    public Order save(Order order) {
        String sql = "INSERT INTO orders (user_id, total, status, created_at) VALUES (?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        if (order.getStatus() == null) {
            order.setStatus("pending");
        }

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[] { "id" });
            ps.setInt(1, order.getUserId());
            ps.setInt(2, order.getTotal());
            ps.setString(3, order.getStatus());
            ps.setTimestamp(4, Timestamp.valueOf(order.getCreatedAt()));
            return ps;
        }, keyHolder);

        order.setId(keyHolder.getKey().intValue());
        return order;
    }

    // Luu chỉ tiết đơn hàng vào cơ sở dữ liệu
    public void saveOrderDetail(OrderDetailRequest orderDetailRequest) {
        String sql = "INSERT INTO order_details (order_id, drink_id, quantity, price, total) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, orderDetailRequest.getOrderId(), orderDetailRequest.getDrinkId(),
                orderDetailRequest.getQuantity(), orderDetailRequest.getPrice(), orderDetailRequest.getTotal());
    }

    // cập nhật total
    public void updateTotal(int orderId, int total) {
        String sql = "UPDATE orders SET total = ? WHERE id = ?";
        jdbcTemplate.update(sql, total, orderId);
    }

    // Lay don order theo ID
    public OrderResponse findById(int id) {
        String sql = "SELECT * FROM orders WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[] { id }, (rs, rowNum) -> {
            OrderResponse order = new OrderResponse();
            order.setId(rs.getInt("id"));
            order.setUserId(rs.getInt("user_id"));
            order.setTotal(rs.getInt("total"));
            order.setStatus(rs.getString("status"));
            order.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime().toString());
            return order;
        });

    }

    // lấy danh sách đơn hàng theo id
    public List<OrderDetailResponse> getOrderDetailsById(int orderId) {
        String sql = "SELECT * FROM order_details WHERE order_id = ?";
        return jdbcTemplate.query(sql, new Object[] { orderId }, (rs, rowNum) -> {
            OrderDetailResponse orderDetail = new OrderDetailResponse();
            orderDetail.setOrderId(rs.getInt("order_id"));
            orderDetail.setDrinkId(rs.getInt("drink_id"));
            orderDetail.setQuantity(rs.getInt("quantity"));
            orderDetail.setPrice(rs.getInt("price"));
            orderDetail.setTotal(rs.getInt("total"));
            return orderDetail;
        });
    }

    // Lấy tất cả đơn hàng
    public List<OrderResponse> findAll() {
        String sql = "SELECT * FROM orders";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            OrderResponse order = new OrderResponse();
            order.setId(rs.getInt("id"));
            order.setUserId(rs.getInt("user_id"));
            order.setTotal(rs.getInt("total"));
            order.setStatus(rs.getString("status"));
            order.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime().toString());
            return order;
        });
    }

    // Lấy tất cả đơn hàng theo userId
    public List<OrderResponse> findAllByUserId(int userId) {
        String sql = "SELECT * FROM orders WHERE user_id = ?";
        return jdbcTemplate.query(sql, new Object[] { userId }, (rs, rowNum) -> {
            OrderResponse order = new OrderResponse();
            order.setId(rs.getInt("id"));
            order.setUserId(rs.getInt("user_id"));
            order.setTotal(rs.getInt("total"));
            order.setStatus(rs.getString("status"));
            order.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime().toString());
            return order;
        });
    }

    // Lấy order theo status
    public List<OrderResponse> findAllByStatus(String status) {
        String sql = "SELECT * FROM orders WHERE status = ?";
        return jdbcTemplate.query(sql, new Object[] { status }, (rs, rowNum) -> {
            OrderResponse order = new OrderResponse();
            order.setId(rs.getInt("id"));
            order.setUserId(rs.getInt("user_id"));
            order.setTotal(rs.getInt("total"));
            order.setStatus(rs.getString("status"));
            order.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime().toString());
            return order;
        });
    }

    // Cập nhật status
    public OrderResponse updateStatus(int orderId, String status) {
        String sql = "UPDATE orders SET status = ? WHERE id = ?";
        jdbcTemplate.update(sql, status, orderId);

        // Lấy thông tin đơn hàng đã cập nhật
        return findById(orderId);
    }
}
