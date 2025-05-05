package com.springboot.springboot.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class InvoiceRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void createInvoice(int orderId) {
        String sql = "INSERT INTO invoices (order_id, created_at) VALUES (?, NOW())";
        jdbcTemplate.update(sql, orderId);
    }
}
