package com.springboot.springboot.repository;

import com.springboot.springboot.model.Drink;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DrinkRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // RowMapper để ánh xạ dữ liệu từ ResultSet vào Drink object
    private RowMapper<Drink> drinkRowMapper = (rs, rowNum) -> {
        Drink drink = new Drink();
        drink.setId(rs.getInt("id"));
        drink.setName(rs.getString("name"));
        drink.setThumbnail(rs.getString("thumbnail"));
        drink.setDescription(rs.getString("description"));
        drink.setPrice(rs.getString("price"));
        drink.setStock(rs.getInt("stock"));
        drink.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
        drink.setUpdatedAt(rs.getTimestamp("updated_at").toLocalDateTime());
        drink.setCategoryId(rs.getInt("category_id"));
        return drink;
    };

    // Thêm sản phẩm mới
    public int save(Drink drink) {
        String sql = "INSERT INTO drink (name, thumbnail, description, price, stock, created_at, updated_at, category_id) VALUES (?, ?, ?, ?, ?, NOW(), NOW(), ?)";
        return jdbcTemplate.update(sql, drink.getName(), drink.getThumbnail(), drink.getDescription(), drink.getPrice(), drink.getStock(), drink.getCategoryId());
    }

    // Lấy danh sách tất cả sản phẩm
    public List<Drink> findAll() {
        String sql = "SELECT * FROM drink";
        return jdbcTemplate.query(sql, drinkRowMapper);
    }

    // Lấy sản phẩm theo ID
    public Drink findById(int id) {
        String sql = "SELECT * FROM drink WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, drinkRowMapper, id);
    }

    // Lấy sản phẩm theo Category ID
    public List<Drink> findByCategoryId(int categoryId) {
        String sql = "SELECT * FROM drink WHERE category_id = ?";
        return jdbcTemplate.query(sql, drinkRowMapper, categoryId);
    }

    // Cập nhật sản phẩm
    public int update(Drink drink) {
        String sql = "UPDATE drink SET name = ?, thumbnail = ?, description = ?, price = ?, stock = ?, updated_at = NOW(), category_id = ? WHERE id = ?";
        return jdbcTemplate.update(sql, drink.getName(), drink.getThumbnail(), drink.getDescription(), drink.getPrice(), drink.getStock(), drink.getCategoryId(), drink.getId());
    }

    // Xóa sản phẩm
    public int delete(int id) {
        String sql = "DELETE FROM drink WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }
}
