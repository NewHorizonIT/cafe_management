package com.springboot.springboot.repository;

import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.springboot.springboot.model.OrderDetail;

@Repository
public class OrderDetailRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Save an order detail
    public void saveOrderDetail(OrderDetail orderDetail) {
        String sql = "INSERT INTO order_details (order_id, product_id, quantity, price) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, orderDetail.getOrderId(), orderDetail.getDrinkId(), orderDetail.getQuantity(),
                orderDetail.getPrice());
    }

    // Find an order detail by ID
    public OrderDetail findOrderDetailById(int id) {
        String sql = "SELECT * FROM order_details WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[] { id }, new OrderDetailRowMapper());
    }

    // Find order details by order ID
    public List<OrderDetail> findByOrderId(int orderId) {
        String sql = "SELECT * FROM order_details WHERE order_id = ?";
        return jdbcTemplate.query(sql, new Object[] { orderId }, new OrderDetailRowMapper());
    }

    // Custom RowMapper for OrderDetail
    private static class OrderDetailRowMapper implements RowMapper<OrderDetail> {
        @Override
        public OrderDetail mapRow(ResultSet rs, int rowNum) throws SQLException {
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrderId(rs.getInt("order_id"));
            orderDetail.setDrinkId(rs.getInt("product_id"));
            orderDetail.setQuantity(rs.getInt("quantity"));
            orderDetail.setPrice(rs.getInt("price"));
            return orderDetail;
        }
    }

    public List<OrderDetail> findAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }
}
