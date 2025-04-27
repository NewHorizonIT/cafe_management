package com.springboot.springboot.repository;

import com.springboot.springboot.entity.User;
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
import java.util.*;

@Repository
public class UserRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private RowMapper<User> userRowMapper = (ResultSet rs, int rowNum) -> {
        User user = new User();
        user.setId(rs.getInt("id"));
        user.setUsername(rs.getString("username"));
        user.setEmail(rs.getString("email"));
        user.setPhone(rs.getString("phone"));
        user.setPassword(rs.getString("password"));
        user.setStatus(rs.getString("status"));
        user.setCreatedAt(
                rs.getTimestamp("created_at") != null ? rs.getTimestamp("created_at").toLocalDateTime() : null);
        user.setUpdatedAt(
                rs.getTimestamp("updated_at") != null ? rs.getTimestamp("updated_at").toLocalDateTime() : null);
        return user;
    };

    // Create
    public User save(User user, int roleId) {
        // SQL để chèn user vào bảng users
        String sql = "INSERT INTO users (username, email, phone, password, status, created_at, updated_at) VALUES (?, ?, ?, ?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        // Chèn user vào bảng users
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[] { "id" });
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPhone());
            ps.setString(4, user.getPassword());
            ps.setString(5, user.getStatus());
            ps.setTimestamp(6, Timestamp.valueOf(user.getCreatedAt()));
            ps.setTimestamp(7, Timestamp.valueOf(user.getUpdatedAt()));
            return ps;
        }, keyHolder);

        // Lấy id của user vừa được lưu
        int userId = keyHolder.getKey().intValue();
        user.setId(userId);

        // Gán role cho user
        String sql1 = "INSERT INTO users_roles (user_id, role_id) VALUES (?, ?)";
        jdbcTemplate.update(sql1, userId, roleId);

        return user;
    }


    // Read (lấy tất cả người dùng)
    public List<User> findAll() {
        String sql = "SELECT * FROM users";
        return jdbcTemplate.query(sql, userRowMapper);
    }

    // Read (lấy người dùng theo ID)
    public Optional<User> findById(int id) {
        String sql = "SELECT * FROM users WHERE id = ?";
        List<User> users = jdbcTemplate.query(sql, userRowMapper, id);
        return users.isEmpty() ? Optional.empty() : Optional.of(users.get(0));
    }

    // Update
    public void update(User user) {
        String sql = "UPDATE users SET username = ?, email = ?, phone = ?, status = ?, updated_at = ? WHERE id = ?";
        jdbcTemplate.update(sql,
                user.getUsername(),
                user.getEmail(),
                user.getPhone(),
                user.getStatus(),
                Timestamp.valueOf(user.getUpdatedAt()),
                user.getId());
    }

    // Delete
    public void deleteById(int id) {
        String sql = "DELETE FROM users WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    // Kiểm tra username đã tồn tại
    public boolean existsByUsername(String username) {
        String sql = "SELECT COUNT(*) FROM users WHERE username = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, username);
        return count != null && count > 0;
    }

    // Kiểm tra email đã tồn tại
    public boolean existsByEmail(String email) {
        String sql = "SELECT COUNT(*) FROM users WHERE email = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, email);
        return count != null && count > 0;
    }

    // Kiểm tra phone đã tồn tại
    public boolean existsByPhone(String phone) {
        String sql = "SELECT COUNT(*) FROM users WHERE phone = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, phone);
        return count != null && count > 0;
    }

    // Tìm người dùng theo username
    public Optional<User> findByUsername(String username) {
        String sql = "SELECT * FROM users WHERE username = ?";
        List<User> users = jdbcTemplate.query(sql, userRowMapper, username);
        return users.isEmpty() ? Optional.empty() : Optional.of(users.get(0));
    }

    // Lấy danh sách role của một user
    public List<Integer> findRoleIdsByUserId(int userId) {
        String sql = "SELECT role_id FROM users_roles WHERE user_id = ?";
        return jdbcTemplate.query(sql, (rs, rowNum) -> rs.getInt("role_id"), userId);
    }

    // Gán role cho user
    public void assignRole(int userId, int roleId) {
        String sql = "INSERT INTO users_roles (user_id, role_id) VALUES (?, ?)";
        jdbcTemplate.update(sql, userId, roleId);
    }

    // Xóa role của user
    public void removeRole(int userId, int roleId) {
        String sql = "DELETE FROM users_roles WHERE user_id = ? AND role_id = ?";
        jdbcTemplate.update(sql, userId, roleId);
    }

    // Lấy roles và permissions của user
    public Map<String, List<String>> findRolesAndPermissionsByUserId(int userId) {
        Map<String, List<String>> rolesAndPermissions = new HashMap<>();

        // Lấy danh sách roles của user
        String rolesSql = """
                SELECT r.role
                FROM roles r
                JOIN users_roles ur ON r.id = ur.role_id
                WHERE ur.user_id = ?
                """;
        List<String> roles = jdbcTemplate.query(rolesSql, (rs, rowNum) -> rs.getString("role"), userId);
        rolesAndPermissions.put("roles", roles);

        // Lấy danh sách permissions của user thông qua roles
        String permissionsSql = """
                SELECT DISTINCT p.name
                FROM permissions p
                JOIN roles_permissions rp ON p.id = rp.permission_id
                JOIN users_roles ur ON rp.role_id = ur.role_id
                WHERE ur.user_id = ?
                """;
        List<String> permissions = jdbcTemplate.query(permissionsSql, (rs, rowNum) -> rs.getString("name"), userId);
        rolesAndPermissions.put("role_permissions", permissions);

        return rolesAndPermissions;
    }

}
