package com.springboot.springboot.repository;

import com.springboot.springboot.model.Supplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SupplierRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // RowMapper để ánh xạ dữ liệu từ ResultSet vào Supplier object
    private RowMapper<Supplier> supplierRowMapper = (rs, rowNum) -> {
        Supplier supplier = new Supplier();
        supplier.setId(rs.getInt("id"));
        supplier.setSupplier(rs.getString("supplier"));
        supplier.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
        return supplier;
    };

    // Thêm nhà cung cấp mới
    public int save(Supplier supplier) {
        String sql = "INSERT INTO supplier (supplier, created_at) VALUES (?, NOW())";
        return jdbcTemplate.update(sql, supplier.getSupplier());
    }

    // Lấy danh sách tất cả nhà cung cấp
    public List<Supplier> findAll() {
        String sql = "SELECT * FROM supplier";
        return jdbcTemplate.query(sql, supplierRowMapper);
    }

    // Lấy nhà cung cấp theo ID
    public Supplier findById(int id) {
        String sql = "SELECT * FROM supplier WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, supplierRowMapper, id);
    }

    // Cập nhật nhà cung cấp
    public int update(Supplier supplier) {
        String sql = "UPDATE supplier SET supplier = ? WHERE id = ?";
        return jdbcTemplate.update(sql, supplier.getSupplier(), supplier.getId());
    }

    // Xóa nhà cung cấp
    public int delete(int id) {
        String sql = "DELETE FROM supplier WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }
}
