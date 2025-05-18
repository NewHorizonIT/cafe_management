package com.springboot.springboot.repository;

import com.springboot.springboot.entity.User;
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
import java.util.Map;
import java.util.HashMap;
import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Repository
public class UserRepository {

    private static final Logger logger = LoggerFactory.getLogger(UserRepository.class);

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
        String sql = "INSERT INTO users (username, email, phone, address, password, status, created_at, updated_at) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        try {
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(sql, new String[] { "id" });
                ps.setString(1, user.getUsername());
                ps.setString(2, user.getEmail());
                ps.setString(3, user.getPhone());
                ps.setString(4, user.getAddress());
                ps.setString(5, user.getPassword());
                ps.setString(6, user.getStatus());
                ps.setTimestamp(7, Timestamp.valueOf(user.getCreatedAt()));
                ps.setTimestamp(8, Timestamp.valueOf(user.getUpdatedAt()));
                return ps;
            }, keyHolder);

            int userId = keyHolder.getKey().intValue();
            user.setId(userId);

            String sql1 = "INSERT INTO users_roles (user_id, role_id) VALUES (?, ?)";
            jdbcTemplate.update(sql1, userId, roleId);

            logger.info("User saved with ID: {}", userId);
            return user;
        } catch (Exception e) {
            logger.error("Error saving user: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to save user", e);
        }
    }

    // Read (lấy tất cả người dùng)
    public List<User> findAll() {
        String sql = "SELECT * FROM users";
        try {
            return jdbcTemplate.query(sql, userRowMapper);
        } catch (Exception e) {
            logger.error("Error fetching all users: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to fetch users", e);
        }
    }

    // Read (lấy người dùng theo ID)
    public Optional<User> findById(int id) {
        String sql = "SELECT * FROM users WHERE id = ?";
        try {
            List<User> users = jdbcTemplate.query(sql, userRowMapper, id);
            return users.isEmpty() ? Optional.empty() : Optional.of(users.get(0));
        } catch (Exception e) {
            logger.error("Error fetching user by ID {}: {}", id, e.getMessage(), e);
            throw new RuntimeException("Failed to fetch user by ID", e);
        }
    }

    // Update
    public void update(User user) {
        String sql = "UPDATE users SET username = ?, email = ?, phone = ?, status = ?, updated_at = ? WHERE id = ?";
        try {
            jdbcTemplate.update(sql,
                    user.getUsername(),
                    user.getEmail(),
                    user.getPhone(),
                    user.getStatus(),
                    Timestamp.valueOf(user.getUpdatedAt()),
                    user.getId());
            logger.info("User updated with ID: {}", user.getId());
        } catch (Exception e) {
            logger.error("Error updating user with ID {}: {}", user.getId(), e.getMessage(), e);
            throw new RuntimeException("Failed to update user", e);
        }
    }

    // Delete
    public void deleteById(int id) {
        String sql = "DELETE FROM users WHERE id = ?";
        try {
            jdbcTemplate.update(sql, id);
            logger.info("User deleted with ID: {}", id);
        } catch (Exception e) {
            logger.error("Error deleting user with ID {}: {}", id, e.getMessage(), e);
            throw new RuntimeException("Failed to delete user", e);
        }
    }

    // Kiểm tra username đã tồn tại
    public boolean existsByUsername(String username) {
        String sql = "SELECT COUNT(*) FROM users WHERE username = ?";
        try {
            Integer count = jdbcTemplate.queryForObject(sql, Integer.class, username);
            return count != null && count > 0;
        } catch (Exception e) {
            logger.error("Error checking username existence {}: {}", username, e.getMessage(), e);
            throw new RuntimeException("Failed to check username existence", e);
        }
    }

    // Kiểm tra email đã tồn tại
    public boolean existsByEmail(String email) {
        String sql = "SELECT COUNT(*) FROM users WHERE email = ?";
        try {
            Integer count = jdbcTemplate.queryForObject(sql, Integer.class, email);
            return count != null && count > 0;
        } catch (Exception e) {
            logger.error("Error checking email existence {}: {}", email, e.getMessage(), e);
            throw new RuntimeException("Failed to check email existence", e);
        }
    }

    // Kiểm tra phone đã tồn tại
    public boolean existsByPhone(String phone) {
        String sql = "SELECT COUNT(*) FROM users WHERE phone = ?";
        try {
            Integer count = jdbcTemplate.queryForObject(sql, Integer.class, phone);
            return count != null && count > 0;
        } catch (Exception e) {
            logger.error("Error checking phone existence {}: {}", phone, e.getMessage(), e);
            throw new RuntimeException("Failed to check phone existence", e);
        }
    }

    // Tìm người dùng theo username
    public Optional<User> findByUsername(String username) {
        String sql = "SELECT * FROM users WHERE username = ?";
        try {
            logger.debug("Executing findByUsername with username: {}", username);
            List<User> users = jdbcTemplate.query(sql, userRowMapper, username);
            if (users.isEmpty()) {
                logger.warn("No user found with username: {}", username);
                return Optional.empty();
            }
            logger.info("User found with username: {}", username);
            return Optional.of(users.get(0));
        } catch (Exception e) {
            logger.error("Error fetching user by username {}: {}", username, e.getMessage(), e);
            throw new RuntimeException("Failed to fetch user by username: " + username, e);
        }
    }

    // Tìm người dùng theo email
    public Optional<User> findByEmail(String email) {
        String sql = "SELECT * FROM users WHERE email = ?";
        try {
            List<User> users = jdbcTemplate.query(sql, userRowMapper, email);
            return users.isEmpty() ? Optional.empty() : Optional.of(users.get(0));
        } catch (Exception e) {
            logger.error("Error fetching user by email {}: {}", email, e.getMessage(), e);
            throw new RuntimeException("Failed to fetch user by email", e);
        }
    }

    // Lấy danh sách role của một user
    public List<Integer> findRoleIdsByUserId(int userId) {
        String sql = "SELECT role_id FROM users_roles WHERE user_id = ?";
        try {
            return jdbcTemplate.query(sql, (rs, rowNum) -> rs.getInt("role_id"), userId);
        } catch (Exception e) {
            logger.error("Error fetching role IDs for user ID {}: {}", userId, e.getMessage(), e);
            throw new RuntimeException("Failed to fetch role IDs", e);
        }
    }

    // Gán role cho user
    public void assignRole(int userId, int roleId) {
        String sql = "INSERT INTO users_roles (user_id, role_id) VALUES (?, ?)";
        try {
            jdbcTemplate.update(sql, userId, roleId);
            logger.info("Assigned role {} to user {}", roleId, userId);
        } catch (Exception e) {
            logger.error("Error assigning role {} to user {}: {}", roleId, userId, e.getMessage(), e);
            throw new RuntimeException("Failed to assign role", e);
        }
    }

    // Xóa role của user
    public void removeRole(int userId, int roleId) {
        String sql = "DELETE FROM users_roles WHERE user_id = ? AND role_id = ?";
        try {
            jdbcTemplate.update(sql, userId, roleId);
            logger.info("Removed role {} from user {}", roleId, userId);
        } catch (Exception e) {
            logger.error("Error removing role {} from user {}: {}", roleId, userId, e.getMessage(), e);
            throw new RuntimeException("Failed to remove role", e);
        }
    }

    // Lấy roles và permissions của user
    public Map<String, List<String>> findRolesAndPermissionsByUserId(int userId) {
        Map<String, List<String>> rolesAndPermissions = new HashMap<>();

        try {
            String rolesSql = """
                    SELECT r.role
                    FROM roles r
                    JOIN users_roles ur ON r.id = ur.role_id
                    WHERE ur.user_id = ?
                    """;
            List<String> roles = jdbcTemplate.query(rolesSql, (rs, rowNum) -> rs.getString("role"), userId);
            rolesAndPermissions.put("roles", roles);

            String permissionsSql = """
                    SELECT DISTINCT p.name
                    FROM permissions p
                    JOIN roles_permissions rp ON p.id = rp.permission_id
                    JOIN users_roles ur ON rp.role_id = ur.role_id
                    WHERE ur.user_id = ?
                    """;
            List<String> permissions = jdbcTemplate.query(permissionsSql, (rs, rowNum) -> rs.getString("name"), userId);
            rolesAndPermissions.put("role_permissions", permissions);

            logger.info("Fetched roles and permissions for user ID: {}", userId);
            return rolesAndPermissions;
        } catch (Exception e) {
            logger.error("Error fetching roles and permissions for user ID {}: {}", userId, e.getMessage(), e);
            throw new RuntimeException("Failed to fetch roles and permissions", e);
        }
    }
}