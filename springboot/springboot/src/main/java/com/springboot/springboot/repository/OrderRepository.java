package com.springboot.springboot.repository;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.springboot.springboot.model.Order;

@Repository
public class OrderRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Order findById(int orderId) {
        String sql = "SELECT * FROM orders WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[] { orderId }, (rs, rowNum) -> {
            Order order = new Order();
            order.setId(rs.getInt("id"));
            order.setUserId(rs.getInt("user_id"));
            order.setTotal(rs.getInt("total"));
            return order;
        });
    }

    public Order save(Order order) {
        String sql = "INSERT INTO orders (user_id, total) VALUES (?, ?) RETURNING id";
        int id = jdbcTemplate.queryForObject(sql, new Object[] { order.getUserId(), order.getTotal() },
                Integer.class);
        order.setId(id);
        return order;
    }

    public Collection<Order> findAll() {
        String sql = "SELECT * FROM orders";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Order order = new Order();
            order.setId(rs.getInt("id"));
            order.setUserId(rs.getInt("user_id"));
            order.setTotal(rs.getInt("total"));
            order.setStatus(rs.getInt("status"));
            order.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
            return order;
        });
    }
}
