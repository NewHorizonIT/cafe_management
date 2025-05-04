package com.springboot.springboot.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.springboot.springboot.entity.Drink;

import java.util.List;

@Repository
public class DrinkRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Lưu thông tin sản phẩm
    public void save(Drink drink) {
        String sql = "INSERT INTO drinks (name, thumbnail, description, price, category_id, created_at, updated_at) VALUES (?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, drink.getName(), drink.getThumbnail(), drink.getDescription(), drink.getPrice(),
                drink.getCategoryId(), drink.getCreatedAt(), drink.getUpdatedAt());
    }

    // Lấy danh sách sản phẩm
    public List<Drink> findAll() {
        String sql = "SELECT * FROM drinks";
        return jdbcTemplate.query(sql, new RowMapper<Drink>() {
            @Override
            public Drink mapRow(java.sql.ResultSet rs, int rowNum) throws java.sql.SQLException {
                Drink drink = new Drink();
                drink.setId(rs.getInt("id"));
                drink.setName(rs.getString("name"));
                drink.setThumbnail(rs.getString("thumbnail"));
                drink.setDescription(rs.getString("description"));
                drink.setPrice(rs.getInt("price"));
                drink.setCategoryId(rs.getInt("category_id"));
                drink.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
                drink.setUpdatedAt(rs.getTimestamp("updated_at").toLocalDateTime());
                return drink;
            }
        });
    }

    // Lấy thông tin sản phẩm theo id
    public Drink findById(int id) {
        String sql = "SELECT * FROM drinks WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[] { id }, new RowMapper<Drink>() {
            @Override
            public Drink mapRow(java.sql.ResultSet rs, int rowNum) throws java.sql.SQLException {
                Drink drink = new Drink();
                drink.setId(rs.getInt("id"));
                drink.setName(rs.getString("name"));
                drink.setThumbnail(rs.getString("thumbnail"));
                drink.setDescription(rs.getString("description"));
                drink.setPrice(rs.getInt("price"));
                drink.setCategoryId(rs.getInt("category_id"));
                drink.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
                drink.setUpdatedAt(rs.getTimestamp("updated_at").toLocalDateTime());
                return drink;
            }
        });
    }

}