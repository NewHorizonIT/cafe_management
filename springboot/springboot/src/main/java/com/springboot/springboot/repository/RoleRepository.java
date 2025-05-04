package com.springboot.springboot.repository;

import com.springboot.springboot.entity.Role;
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
public class RoleRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public RoleRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private RowMapper<Role> roleRowMapper = (ResultSet rs, int rowNum) -> {
        Role role = new Role();
        role.setId(rs.getInt("id"));
        role.setName(rs.getString("role"));
        role.setCreatedAt(
                rs.getTimestamp("created_at") != null ? rs.getTimestamp("created_at").toLocalDateTime() : null);
        role.setUpdatedAt(
                rs.getTimestamp("updated_at") != null ? rs.getTimestamp("updated_at").toLocalDateTime() : null);
        return role;
    };

    // Create
    public Role save(Role role) {
        String sql = "INSERT INTO roles (role, created_at, updated_at) VALUES (?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[] { "id" });
            ps.setString(1, role.getName());
            ps.setTimestamp(2, Timestamp.valueOf(role.getCreatedAt()));
            ps.setTimestamp(3, Timestamp.valueOf(role.getUpdatedAt()));
            return ps;
        }, keyHolder);

        role.setId(keyHolder.getKey().intValue());
        return role;
    }

    // Read (lấy tất cả roles)
    public List<Role> findAll() {
        String sql = "SELECT * FROM roles";
        return jdbcTemplate.query(sql, roleRowMapper);
    }

    // Read (lấy role theo ID)
    public Optional<Role> findById(int id) {
        String sql = "SELECT * FROM roles WHERE id = ?";
        List<Role> roles = jdbcTemplate.query(sql, roleRowMapper, id);
        return roles.isEmpty() ? Optional.empty() : Optional.of(roles.getFirst());
    }

    // Read (Lấy role theo tên)
    public Optional<Role> findByName(String name) {
        String sql = "SELECT * FROM roles WHERE role = ?";
        List<Role> roles = jdbcTemplate.query(sql, roleRowMapper, name);
        return roles.isEmpty() ? Optional.empty() : Optional.of(roles.getFirst());
    }

    // Update
    public void update(Role role) {
        String sql = "UPDATE roles SET role = ?, updated_at = ? WHERE id = ?";
        jdbcTemplate.update(sql,
                role.getName(),
                Timestamp.valueOf(role.getUpdatedAt()),
                role.getId());
    }



    // Delete
    public void deleteById(int id) {
        String sql = "DELETE FROM roles WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    // Kiểm tra name đã tồn tại
    public boolean existsByName(String name) {
        String sql = "SELECT COUNT(*) FROM roles WHERE role = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, name);
        return count != null && count > 0;
    }

    // Lấy danh sách permission của một role
    public List<Integer> findPermissionIdsByRoleId(int roleId) {
        String sql = "SELECT permission_id FROM roles_permissions WHERE role_id = ?";
        return jdbcTemplate.query(sql, (rs, rowNum) -> rs.getInt("permission_id"), roleId);
    }

    // Gán permission cho role
    public void assignPermission(int roleId, int permissionId) {
        String sql = "INSERT INTO roles_permissions (role_id, permission_id) VALUES (?, ?)";
        jdbcTemplate.update(sql, roleId, permissionId);
    }

    // Xóa permission của role
    public void removePermission(int roleId, int permissionId) {
        String sql = "DELETE FROM roles_permissions WHERE role_id = ? AND permission_id = ?";
        jdbcTemplate.update(sql, roleId, permissionId);
    }
}
