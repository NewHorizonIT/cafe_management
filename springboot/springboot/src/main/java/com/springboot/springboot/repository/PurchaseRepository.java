package com.springboot.springboot.repository;

import com.springboot.springboot.model.Purchase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PurchaseRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private RowMapper<Purchase> purchaseRowMapper = (rs, rowNum) -> {
        Purchase purchase = new Purchase();
        purchase.setPurchaseId(rs.getInt("purchase_id"));
        purchase.setDate(rs.getTimestamp("date").toLocalDateTime());
        purchase.setTotal(rs.getInt("total"));
        purchase.setStatus(rs.getInt("status"));
        purchase.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
        purchase.setUpdatedAt(rs.getTimestamp("updated_at").toLocalDateTime());
        purchase.setSupplierId(rs.getInt("supplier_id"));
        purchase.setStaffId(rs.getInt("staff_id"));
        return purchase;
    };

    // Tạo phiếu nhập kho mới
    public int save(Purchase purchase) {
        String sql = "INSERT INTO purchase (date, total, status, created_at, updated_at, supplier_id, staff_id) VALUES (?, ?, ?, NOW(), NOW(), ?, ?)";
        return jdbcTemplate.update(sql, purchase.getDate(), purchase.getTotal(), purchase.getStatus(), purchase.getSupplierId(), purchase.getStaffId());
    }

    // Lấy danh sách phiếu nhập kho
    public List<Purchase> findAll() {
        String sql = "SELECT * FROM purchase";
        return jdbcTemplate.query(sql, purchaseRowMapper);
    }

    // Lấy phiếu nhập kho theo ID
    public Purchase findById(int id) {
        String sql = "SELECT * FROM purchase WHERE purchase_id = ?";
        return jdbcTemplate.queryForObject(sql, purchaseRowMapper, id);
    }

    // Cập nhật phiếu nhập kho
    public int update(Purchase purchase) {
        String sql = "UPDATE purchase SET date = ?, total = ?, status = ?, updated_at = NOW(), supplier_id = ?, staff_id = ? WHERE purchase_id = ?";
        return jdbcTemplate.update(sql, purchase.getDate(), purchase.getTotal(), purchase.getStatus(), purchase.getSupplierId(), purchase.getStaffId(), purchase.getPurchaseId());
    }

    // Xóa phiếu nhập kho
    public int delete(int id) {
        String sql = "DELETE FROM purchase WHERE purchase_id = ?";
        return jdbcTemplate.update(sql, id);
    }
}
