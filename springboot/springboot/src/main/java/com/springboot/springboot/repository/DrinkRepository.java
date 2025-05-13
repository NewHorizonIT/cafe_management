package com.springboot.springboot.repository;

import com.springboot.springboot.entity.Drink;
import com.springboot.springboot.model.DrinkMaterial;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DrinkRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    private static final String BASE_IMAGE_URL = "http://localhost:8080/images/";

    // RowMapper để ánh xạ dữ liệu từ ResultSet vào Drink object
    private RowMapper<Drink> drinkRowMapper = (rs, rowNum) -> {
        Drink drink = new Drink();
        drink.setId(rs.getInt("id"));
        drink.setName(rs.getString("name"));
        drink.setThumbnail(rs.getString("thumbnail"));
        drink.setDescription(rs.getString("description"));
        drink.setPrice(rs.getInt("price"));
        drink.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
        drink.setUpdatedAt(rs.getTimestamp("updated_at").toLocalDateTime());
        drink.setCategoryId(rs.getInt("category_id"));

        // Tạo URL đầy đủ cho ảnh
        String thumbnail = rs.getString("thumbnail");
        if (thumbnail != null && !thumbnail.isEmpty()) {
            drink.setThumbnail(BASE_IMAGE_URL + thumbnail);
        } else {
            drink.setThumbnail(null); // Hoặc gán giá trị mặc định nếu không có thumbnail
        }
        return drink;
    };

    // RowMapper để ánh xạ DrinkMaterial
    private RowMapper<DrinkMaterial> drinkMaterialRowMapper = (rs, rowNum) -> {
        DrinkMaterial drinkMaterial = new DrinkMaterial();
        drinkMaterial.setDrinkId(rs.getInt("drink_id"));
        drinkMaterial.setMaterialId(rs.getInt("material_id"));
        drinkMaterial.setQuantity(rs.getInt("quantity"));
        return drinkMaterial;
    };

    // Thêm sản phẩm mới
    public int save(Drink drink) {
        if (drink == null || drink.getName() == null || drink.getName().isEmpty()) {
            throw new IllegalArgumentException("Drink hoặc tên sản phẩm không được để trống");
        }
        String sql = "INSERT INTO drinks (name, thumbnail, description, price, created_at, updated_at, category_id) VALUES (?, ?, ?, ?, ?, NOW(), NOW(), ?)";
        return jdbcTemplate.update(sql, drink.getName(), drink.getThumbnail(), drink.getDescription(), drink.getPrice(),
                drink.getCategoryId());
    }

    // Lấy danh sách tất cả sản phẩm
    public List<Drink> findAll() {
        String sql = "SELECT * FROM drinks";
        return jdbcTemplate.query(sql, drinkRowMapper);
    }

    // Lấy sản phẩm theo tên (tìm kiếm gần đúng)
    public List<Drink> findByName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Tên tìm kiếm không được để trống");
        }
        String sql = "SELECT * FROM drinks WHERE name LIKE ?";
        return jdbcTemplate.query(sql, drinkRowMapper, "%" + name + "%");
    }

    // Lấy sản phẩm theo ID
    public Drink findById(int id) {
        String sql = "SELECT * FROM drinks WHERE id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, drinkRowMapper, id);
        } catch (Exception e) {
            return null; // Không tìm thấy sản phẩm
        }
    }

    // Lấy sản phẩm theo Category ID
    public List<Drink> findByCategoryId(int categoryId) {
        String sql = "SELECT * FROM drinks WHERE category_id = ?";
        return jdbcTemplate.query(sql, drinkRowMapper, categoryId);
    }

    // Kiểm tra ID tồn tại
    public boolean existsById(int id) {
        String sql = "SELECT COUNT(*) FROM drinks WHERE id = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, id);
        return count != null && count > 0;
    }

    // Lấy thumbnail theo ID
    public String getThumbnailById(int id) {
        String sql = "SELECT thumbnail FROM drinks WHERE id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, String.class, id);
        } catch (Exception e) {
            return null; // Không tìm thấy hoặc thumbnail là null
        }
    }

    // Cập nhật sản phẩm
    public int update(Drink drink) {
        if (drink == null || drink.getName() == null || drink.getName().isEmpty()) {
            throw new IllegalArgumentException("Drink hoặc tên sản phẩm không được để trống");
        }
        String sql = "UPDATE drinks SET name = ?, thumbnail = ?, description = ?, price = ?, updated_at = NOW(), category_id = ? WHERE id = ?";
        return jdbcTemplate.update(sql, drink.getName(), drink.getThumbnail(), drink.getDescription(), drink.getPrice(),
                drink.getCategoryId(), drink.getId());
    }

    // Xóa sản phẩm
    public int delete(int id) {
        String sql = "DELETE FROM drinks WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }

    // Thêm Material cần cho Drink
    public int addMaterialToDrink(DrinkMaterial drinkMaterial) {
        String sql = "INSERT INTO drink_material (drink_id, material_id, quantity) VALUES (?, ?, ?)";
        return jdbcTemplate.update(sql, drinkMaterial.getDrinkId(), drinkMaterial.getMaterialId(),
                drinkMaterial.getQuantity());
    }

    // Lấy danh sách Material cần cho Drink
    public List<DrinkMaterial> getMaterialsForDrink(int drinkId) {
        String sql = "SELECT * FROM drink_material WHERE drink_id = ?";
        return jdbcTemplate.query(sql, drinkMaterialRowMapper, drinkId);
    }

    // Xóa tất cả Material liên kết với Drink
    public int deleteMaterialsForDrink(int drinkId) {
        String sql = "DELETE FROM drink_material WHERE drink_id = ?";
        return jdbcTemplate.update(sql, drinkId);
    }
}