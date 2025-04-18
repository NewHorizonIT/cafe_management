package com.springboot.springboot.repository;

import com.springboot.springboot.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CategoryRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private RowMapper<Category> categoryRowMapper = (rs, rowNum) -> {
        Category category = new Category();
        category.setId(rs.getInt("id"));
        category.setName(rs.getString("name"));
        category.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
        return category;
    };

    // Thêm danh mục mới
    public int save(Category category) {
        String sql = "INSERT INTO category (name, created_at) VALUES (?, NOW())";
        return jdbcTemplate.update(sql, category.getName());
    }

    // Lấy danh sách danh mục
    public List<Category> findAll() {
        String sql = "SELECT * FROM category";
        return jdbcTemplate.query(sql, categoryRowMapper);
    }

    // Lấy danh mục theo ID
    public Category findById(int id) {
        String sql = "SELECT * FROM category WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, categoryRowMapper, id);
    }

    // Cập nhật danh mục
    public int update(Category category) {
        String sql = "UPDATE category SET name = ? WHERE id = ?";
        return jdbcTemplate.update(sql, category.getName(), category.getId());
    }

    // Xóa danh mục
    public int delete(int id) {
        String sql = "DELETE FROM category WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }
}
