package com.springboot.springboot.repository;

import com.springboot.springboot.model.Invoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class InvoiceRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Tạo hóa đơn mới
    public void createInvoice(int orderId) {
        String sql = "INSERT INTO invoices (order_id, created_at) VALUES (?, NOW())";
        jdbcTemplate.update(sql, orderId);
    }

    // Lấy tất cả invoices
    public List<Invoice> getAllInvoices() {
        String sql = "SELECT * FROM invoices";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Invoice.class));
    }

    // Lấy danh sách invoices theo order_id
    public List<Invoice> getInvoicesByOrderId(int orderId) {
        String sql = "SELECT * FROM invoices WHERE order_id = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Invoice.class), orderId);
    }

    // Cập nhật order_id của invoice theo invoice_id
    public void updateInvoice(int invoiceId, int newOrderId) {
        String sql = "UPDATE invoices SET order_id = ? WHERE invoice_id = ?";
        jdbcTemplate.update(sql, newOrderId, invoiceId);
    }

    // Xóa hóa đơn theo order_id
    public void deleteInvoicesByOrderId(int orderId) {
        String sql = "DELETE FROM invoices WHERE order_id = ?";
        jdbcTemplate.update(sql, orderId);
    }
}

