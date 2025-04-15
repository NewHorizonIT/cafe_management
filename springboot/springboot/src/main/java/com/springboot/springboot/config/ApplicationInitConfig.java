package com.springboot.springboot.config;

import com.springboot.springboot.constant.PredefinedRole;
import com.springboot.springboot.entity.Permission;
import com.springboot.springboot.entity.Role;
import com.springboot.springboot.entity.User;
import com.springboot.springboot.repository.PermissionRepository;
import com.springboot.springboot.repository.RoleRepository;
import com.springboot.springboot.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;

@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ApplicationInitConfig {

    PasswordEncoder passwordEncoder;
    UserRepository userRepository;
    RoleRepository roleRepository;
    PermissionRepository permissionRepository;

    @NonFinal
    static final String ADMIN_USER_NAME = "admin";

    @NonFinal
    static final String ADMIN_PASSWORD = "Admin@123";

    @Bean
    ApplicationRunner applicationRunner() {
        log.info("Initializing application.....");
        return args -> {
            if (userRepository.findByUsername(ADMIN_USER_NAME).isEmpty()) {
                LocalDateTime now = LocalDateTime.now();

                // Tạo permissions nếu chưa tồn tại
                Permission viewUsers;
                if (!permissionRepository.existsByName("VIEW_USERS")) {
                    viewUsers = permissionRepository.save(Permission.builder()
                            .name("VIEW_USERS")
                            .createdAt(now)
                            .updatedAt(now)
                            .build());
                } else {
                    viewUsers = permissionRepository.findAll().stream()
                            .filter(p -> p.getName().equals("VIEW_USERS"))
                            .findFirst()
                            .orElseThrow(() -> new RuntimeException("VIEW_USERS permission not found"));
                }

                Permission editUsers;
                if (!permissionRepository.existsByName("EDIT_USERS")) {
                    editUsers = permissionRepository.save(Permission.builder()
                            .name("EDIT_USERS")
                            .createdAt(now)
                            .updatedAt(now)
                            .build());
                } else {
                    editUsers = permissionRepository.findAll().stream()
                            .filter(p -> p.getName().equals("EDIT_USERS"))
                            .findFirst()
                            .orElseThrow(() -> new RuntimeException("EDIT_USERS permission not found"));
                }

                Permission deleteUsers;
                if (!permissionRepository.existsByName("DELETE_USERS")) {
                    deleteUsers = permissionRepository.save(Permission.builder()
                            .name("DELETE_USERS")
                            .createdAt(now)
                            .updatedAt(now)
                            .build());
                } else {
                    deleteUsers = permissionRepository.findAll().stream()
                            .filter(p -> p.getName().equals("DELETE_USERS"))
                            .findFirst()
                            .orElseThrow(() -> new RuntimeException("DELETE_USERS permission not found"));
                }

                Permission manageRoles;
                if (!permissionRepository.existsByName("MANAGE_ROLES")) {
                    manageRoles = permissionRepository.save(Permission.builder()
                            .name("MANAGE_ROLES")
                            .createdAt(now)
                            .updatedAt(now)
                            .build());
                } else {
                    manageRoles = permissionRepository.findAll().stream()
                            .filter(p -> p.getName().equals("MANAGE_ROLES"))
                            .findFirst()
                            .orElseThrow(() -> new RuntimeException("MANAGE_ROLES permission not found"));
                }

                Permission viewProducts;
                if (!permissionRepository.existsByName("VIEW_PRODUCTS")) {
                    viewProducts = permissionRepository.save(Permission.builder()
                            .name("VIEW_PRODUCTS")
                            .createdAt(now)
                            .updatedAt(now)
                            .build());
                } else {
                    viewProducts = permissionRepository.findAll().stream()
                            .filter(p -> p.getName().equals("VIEW_PRODUCTS"))
                            .findFirst()
                            .orElseThrow(() -> new RuntimeException("VIEW_PRODUCTS permission not found"));
                }

                // Tạo vai trò USER (BUYER) nếu chưa tồn tại
                Role userRole;
                if (!roleRepository.existsByName(PredefinedRole.USER_ROLE.getRoleName())) {
                    userRole = roleRepository.save(Role.builder()
                            .name(PredefinedRole.USER_ROLE.getRoleName())
                            .createdAt(now)
                            .updatedAt(now)
                            .build());

                    // Gán permission VIEW_PRODUCTS cho USER_ROLE
                    roleRepository.assignPermission(userRole.getId(), viewProducts.getId());
                } else {
                    userRole = roleRepository.findAll().stream()
                            .filter(r -> r.getName().equals(PredefinedRole.USER_ROLE.getRoleName()))
                            .findFirst()
                            .orElseThrow(() -> new RuntimeException("USER_ROLE not found"));
                }

                // Tạo vai trò ADMIN nếu chưa tồn tại
                Role adminRole;
                if (!roleRepository.existsByName(PredefinedRole.ADMIN_ROLE.getRoleName())) {
                    adminRole = roleRepository.save(Role.builder()
                            .name(PredefinedRole.ADMIN_ROLE.getRoleName())
                            .createdAt(now)
                            .updatedAt(now)
                            .build());

                    // Gán permissions cho ADMIN_ROLE
                    roleRepository.assignPermission(adminRole.getId(), viewUsers.getId());
                    roleRepository.assignPermission(adminRole.getId(), editUsers.getId());
                    roleRepository.assignPermission(adminRole.getId(), deleteUsers.getId());
                    roleRepository.assignPermission(adminRole.getId(), manageRoles.getId());
                } else {
                    adminRole = roleRepository.findAll().stream()
                            .filter(r -> r.getName().equals(PredefinedRole.ADMIN_ROLE.getRoleName()))
                            .findFirst()
                            .orElseThrow(() -> new RuntimeException("ADMIN_ROLE not found"));
                }

                // Tạo tài khoản admin
                User user = User.builder()
                        .username(ADMIN_USER_NAME)
                        .email("admin@example.com")
                        .phone("1234567890")
                        .password(passwordEncoder.encode(ADMIN_PASSWORD))
                        .status("active")
                        .createdAt(now)
                        .updatedAt(now)
                        .build();

                user = userRepository.save(user);

                // Gán vai trò ADMIN cho tài khoản
                userRepository.assignRole(user.getId(), adminRole.getId());

                log.warn("Admin user has been created with default password: {}, please change it", ADMIN_PASSWORD);
            }
            log.info("Application initialization completed .....");
        };
    }
}
