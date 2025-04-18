package com.springboot.springboot.repository;

import com.springboot.springboot.model.Material;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MaterialRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private RowMapper<Material> materialRowMapper = (rs, rowNum) -> {
        Material material = new Material();
        material.setId(rs.getInt("id"));
        material.setName(rs.getString("name"));
        material.setQuantity(rs.getInt("quantity"));
        material.setStatus(rs.getInt("status"));
        material.setDateEntry(rs.getTimestamp("date_entry").toLocalDateTime());
        material.setSupplierId(rs.getInt("supplier_id"));
        return material;
    };

    // Thêm nguyên liệu
    public int save(Material material) {
        String sql = "INSERT INTO material (name, quantity, status, date_entry, supplier_id) VALUES (?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, material.getName(), material.getQuantity(), material.getStatus(), material.getDateEntry(), material.getSupplierId());
    }

    // Cập nhật nguyên liệu
    public int update(Material material) {
        String sql = "UPDATE material SET name = ?, quantity = ?, status = ?, date_entry = ?, supplier_id = ? WHERE id = ?";
        return jdbcTemplate.update(sql, material.getName(), material.getQuantity(), material.getStatus(), material.getDateEntry(), material.getSupplierId(), material.getId());
    }

    // Xóa nguyên liệu
    public int delete(int id) {
        String sql = "DELETE FROM material WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }

    // Lấy tất cả nguyên liệu
    public List<Material> findAll() {
        String sql = "SELECT * FROM material";
        return jdbcTemplate.query(sql, materialRowMapper);
    }

    // Lấy nguyên liệu theo ID
    public Material findById(int id) {
        String sql = "SELECT * FROM material WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, materialRowMapper, id);
    }
}
