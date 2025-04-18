package com.springboot.springboot.repository;

import com.springboot.springboot.entity.InvalidatedToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.Timestamp;

@Repository
public class InvalidatedTokenRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public InvalidatedTokenRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private RowMapper<InvalidatedToken> invalidatedTokenRowMapper = (ResultSet rs, int row) -> {
        InvalidatedToken token = new InvalidatedToken();
        token.setId(rs.getString("id"));
        token.setExpiryTime(rs.getTimestamp("expiry_time"));
        return token;
    };

    // Lưu token vào bảng invalidated_tokens
    public void save(InvalidatedToken invalidatedToken) {
        String sql = "INSERT INTO invalidated_tokens (id, expiry_time) VALUES (?, ?)";
        jdbcTemplate.update(sql,
                invalidatedToken.getId(),
                new Timestamp(invalidatedToken.getExpiryTime().getTime()));
    }

    // Kiểm tra token có tồn tại trong bảng invalidated_tokens hay không
    public boolean existsById(String id) {
        String sql = "SELECT COUNT(*) FROM invalidated_tokens WHERE id = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, id);
        return count != null && count > 0;
    }

    // Lấy token từ bảng invalidated_tokens theo id
    public InvalidatedToken findById(String id) {
        String sql = "SELECT * FROM invalidated_tokens WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, invalidatedTokenRowMapper, id);
    }
}
