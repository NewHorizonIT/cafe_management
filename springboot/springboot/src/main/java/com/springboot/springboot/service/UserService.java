package com.springboot.springboot.service;

import com.springboot.springboot.dto.request.UserCreationRequest;
import com.springboot.springboot.dto.request.UserLoginRequest;
import com.springboot.springboot.dto.request.UserUpdateRequest;
import com.springboot.springboot.dto.response.UserResponse;
import com.springboot.springboot.entity.User;
import com.springboot.springboot.repository.RoleRepository;
import com.springboot.springboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationService authenticationService;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder,
            SecurityFilterChain jwtSecurityFilterChain, AuthenticationService authenticationService) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationService = authenticationService;
    }

    // Lấy user hiện tại
    public UserResponse getCurrentUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found with username: " + username));
        return mapToUserResponse(user);
    }

    // Lấy user theo username
    public UserResponse getUserByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found with username: " + username));
        return mapToUserResponse(user);
    }

    // Create
    public UserResponse createUser(UserCreationRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("Username already exists");
        }
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }
        if (userRepository.existsByPhone(request.getPhone())) {
            throw new RuntimeException("Phone already exists");
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setAddress(request.getAddress());
        user.setStatus("true");
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());

        int roleId = request.getRoleId() != null
                ? request.getRoleId()
                : 1;

        user = userRepository.save(user, roleId);

        // Tạo Token khi tạo user
        String token = authenticationService.generateToken(user);

        return mapToCreateUserResponse(user, roleId, token);
    }

    // Login
    public UserResponse loginUser(UserLoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found with username: " + request.getEmail()));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        // Tạo Token khi đăng nhập
        String token = authenticationService.generateToken(user);

        return mapToCreateUserResponse(user, user.getId(), token);
    }

    // Read (lấy tất cả người dùng)
    public List<UserResponse> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(this::mapToUserResponse)
                .collect(Collectors.toList());
    }

    // Read (lấy người dùng theo ID)
    public UserResponse getUserById(int id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        return mapToUserResponse(user);
    }

    // Update
    public UserResponse updateUser(int id, UserUpdateRequest request) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

        if (request.getUsername() != null && !request.getUsername().equals(user.getUsername())) {
            if (userRepository.existsByUsername(request.getUsername())) {
                throw new RuntimeException("Username already exists");
            }
            user.setUsername(request.getUsername());
        }
        if (request.getEmail() != null && !request.getEmail().equals(user.getEmail())) {
            if (userRepository.existsByEmail(request.getEmail())) {
                throw new RuntimeException("Email already exists");
            }
            user.setEmail(request.getEmail());
        }
        if (request.getPhone() != null && !request.getPhone().equals(user.getPhone())) {
            if (userRepository.existsByPhone(request.getPhone())) {
                throw new RuntimeException("Phone already exists");
            }
            user.setPhone(request.getPhone());
        }

        if (request.getAddress() != null && !request.getAddress().equals(user.getAddress())) {
            user.setAddress(request.getAddress());
        }

        user.setUpdatedAt(LocalDateTime.now());

        userRepository.update(user);
        return mapToUserResponse(user);
    }

    // Delete
    public void deleteUser(int id) {
        if (userRepository.findById(id).isEmpty()) {
            throw new RuntimeException("User not found with id: " + id);
        }
        userRepository.deleteById(id);
    }

    // Gán role cho user
    public void assignRole(int userId, int roleId) {
        if (userRepository.findById(userId).isEmpty()) {
            throw new RuntimeException("User not found with id: " + userId);
        }
        userRepository.assignRole(userId, roleId);
    }

    // Xóa role của user
    public void removeRole(int userId, int roleId) {
        if (userRepository.findById(userId).isEmpty()) {
            throw new RuntimeException("User not found with id: " + userId);
        }
        userRepository.removeRole(userId, roleId);
    }

    // Lấy danh sách role của user
    public List<Integer> getRoleIdsByUserId(int userId) {
        if (userRepository.findById(userId).isEmpty()) {
            throw new RuntimeException("User not found with id: " + userId);
        }
        return userRepository.findRoleIdsByUserId(userId);
    }

    // Helper method: Chuyển entity thành DTO
    private UserResponse mapToUserResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .phone(user.getPhone())
                .status(user.getStatus())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();
    }

    private UserResponse mapToCreateUserResponse(User user, int id, String token) {
        return UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .phone(user.getPhone())
                .status(user.getStatus())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .address(user.getAddress())
                .roles(id)
                .token(token)
                .build();
    }
}
