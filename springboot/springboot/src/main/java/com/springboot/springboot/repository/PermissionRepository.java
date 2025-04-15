package com.springboot.springboot.repository;

import com.springboot.springboot.entity.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Repository
public class PermissionRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PermissionRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private RowMapper<Permission> permissionRowMapper = (ResultSet rs, int rowNum) -> {
        Permission permission = new Permission();
        permission.setId(rs.getInt("id"));
        permission.setName(rs.getString("name"));
        permission.setCreatedAt(rs.getTimestamp("created_at") != null ? rs.getTimestamp("created_at").toLocalDateTime() : null);
        permission.setUpdatedAt(rs.getTimestamp("updated_at") != null ? rs.getTimestamp("updated_at").toLocalDateTime() : null);
        return permission;
    };

    // Create
    public Permission save(Permission permission) {
        String sql = "INSERT INTO permissions (name, created_at, updated_at) VALUES (?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
            ps.setString(1, permission.getName());
            ps.setTimestamp(2, Timestamp.valueOf(permission.getCreatedAt()));
            ps.setTimestamp(3, Timestamp.valueOf(permission.getUpdatedAt()));
            return ps;
        }, keyHolder);

        permission.setId(keyHolder.getKey().intValue());
        return permission;
    }

    // Read (lấy tất cả permissions)
    public List<Permission> findAll() {
        String sql = "SELECT * FROM permissions";
        return jdbcTemplate.query(sql, permissionRowMapper);
    }

    // Read (lấy permission theo ID)
    public Optional<Permission> findById(int id) {
        String sql = "SELECT * FROM permissions WHERE id = ?";
        List<Permission> permissions = jdbcTemplate.query(sql, permissionRowMapper, id);
        return permissions.isEmpty() ? Optional.empty() : Optional.of(permissions.get(0));
    }

    // Update
    public void update(Permission permission) {
        String sql = "UPDATE permissions SET name = ?, updated_at = ? WHERE id = ?";
        jdbcTemplate.update(sql,
                permission.getName(),
                Timestamp.valueOf(permission.getUpdatedAt()),
                permission.getId());
    }

    // Delete
    public void deleteById(int id) {
        String sql = "DELETE FROM permissions WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    // Kiểm tra name đã tồn tại
    public boolean existsByName(String name) {
        String sql = "SELECT COUNT(*) FROM permissions WHERE name = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, name);
        return count != null && count > 0;
    }
}

