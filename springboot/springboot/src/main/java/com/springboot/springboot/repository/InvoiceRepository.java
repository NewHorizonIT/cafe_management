package com.springboot.springboot.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.springboot.springboot.model.Invoice;

@Repository
public class InvoiceRepository {
    @Autowired

    private JdbcTemplate jdbcTemplate;

    public List<Invoice> getAllInvoices() {
        String sql = "SELECT * FROM invoice";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Invoice invoice = new Invoice();
            invoice.setInvoiceID(rs.getInt("invoiceID"));
            invoice.setOrderID(rs.getInt("orderID"));
            invoice.setPayment(rs.getString("payment"));
            invoice.setCreatedAt(rs.getObject("createdAt", LocalDate.class));
            return invoice;
        });
    }

    public Invoice getInvoiceById(int invoiceID) {
        String sql = "SELECT * FROM invoice WHERE invoiceID = ?";
        return jdbcTemplate.queryForObject(sql, new Object[] { invoiceID }, (rs, rowNum) -> {
            Invoice invoice = new Invoice();
            invoice.setInvoiceID(rs.getInt("invoiceID"));
            invoice.setOrderID(rs.getInt("orderID"));
            invoice.setPayment(rs.getString("payment"));
            invoice.setCreatedAt(rs.getObject("createdAt", LocalDate.class));
            return invoice;
        });
    }

    public void createInvoice(Invoice invoice) {
        String sql = "INSERT INTO invoice (orderID, payment, createdAt) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, invoice.getOrderID(), invoice.getPayment(), invoice.getCreatedAt());
    }

    public void updateInvoice(Invoice invoice) {
        String sql = "UPDATE invoice SET orderID = ?, payment = ?, createdAt = ? WHERE invoiceID = ?";
        jdbcTemplate.update(sql, invoice.getOrderID(), invoice.getPayment(), invoice.getCreatedAt(),
                invoice.getInvoiceID());
    }

    public void deleteInvoice(int invoiceID) {
        String sql = "DELETE FROM invoice WHERE invoiceID = ?";
        jdbcTemplate.update(sql, invoiceID);
    }

    public List<Invoice> getInvoicesByOrderId(int orderID) {
        String sql = "SELECT * FROM invoice WHERE orderID = ?";
        return jdbcTemplate.query(sql, new Object[] { orderID }, (rs, rowNum) -> {
            Invoice invoice = new Invoice();
            invoice.setInvoiceID(rs.getInt("invoiceID"));
            invoice.setOrderID(rs.getInt("orderID"));
            invoice.setPayment(rs.getString("payment"));
            invoice.setCreatedAt(rs.getObject("createdAt", LocalDate.class));
            return invoice;
        });
    }

    public List<Invoice> getInvoicesByDate(LocalDate date) {
        String sql = "SELECT * FROM invoice WHERE createdAt = ?";
        return jdbcTemplate.query(sql, new Object[] { date }, (rs, rowNum) -> {
            Invoice invoice = new Invoice();
            invoice.setInvoiceID(rs.getInt("invoiceID"));
            invoice.setOrderID(rs.getInt("orderID"));
            invoice.setPayment(rs.getString("payment"));
            invoice.setCreatedAt(rs.getObject("createdAt", LocalDate.class));
            return invoice;
        });
    }

    public List<Invoice> getInvoicesByPayment(String payment) {
        String sql = "SELECT * FROM invoice WHERE payment = ?";
        return jdbcTemplate.query(sql, new Object[] { payment }, (rs, rowNum) -> {
            Invoice invoice = new Invoice();
            invoice.setInvoiceID(rs.getInt("invoiceID"));
            invoice.setOrderID(rs.getInt("orderID"));
            invoice.setPayment(rs.getString("payment"));
            invoice.setCreatedAt(rs.getObject("createdAt", LocalDate.class));
            return invoice;
        });
    }

    public List<Invoice> getInvoicesByDateRange(LocalDate startDate, LocalDate endDate) {
        String sql = "SELECT * FROM invoice WHERE createdAt BETWEEN ? AND ?";
        return jdbcTemplate.query(sql, new Object[] { startDate, endDate }, (rs, rowNum) -> {
            Invoice invoice = new Invoice();
            invoice.setInvoiceID(rs.getInt("invoiceID"));
            invoice.setOrderID(rs.getInt("orderID"));
            invoice.setPayment(rs.getString("payment"));
            invoice.setCreatedAt(rs.getObject("createdAt", LocalDate.class));
            return invoice;
        });
    }

    public List<Invoice> getInvoicesByOrderIdAndDateRange(int orderID, LocalDate startDate, LocalDate endDate) {
        String sql = "SELECT * FROM invoice WHERE orderID = ? AND createdAt BETWEEN ? AND ?";
        return jdbcTemplate.query(sql, new Object[] { orderID, startDate, endDate }, (rs, rowNum) -> {
            Invoice invoice = new Invoice();
            invoice.setInvoiceID(rs.getInt("invoiceID"));
            invoice.setOrderID(rs.getInt("orderID"));
            invoice.setPayment(rs.getString("payment"));
            invoice.setCreatedAt(rs.getObject("createdAt", LocalDate.class));
            return invoice;
        });
    }

    public List<Invoice> getInvoicesByPaymentAndDateRange(String payment, LocalDate startDate, LocalDate endDate) {
        String sql = "SELECT * FROM invoice WHERE payment = ? AND createdAt BETWEEN ? AND ?";
        return jdbcTemplate.query(sql, new Object[] { payment, startDate, endDate }, (rs, rowNum) -> {
            Invoice invoice = new Invoice();
            invoice.setInvoiceID(rs.getInt("invoiceID"));
            invoice.setOrderID(rs.getInt("orderID"));
            invoice.setPayment(rs.getString("payment"));
            invoice.setCreatedAt(rs.getObject("createdAt", LocalDate.class));
            return invoice;
        });
    }

}
