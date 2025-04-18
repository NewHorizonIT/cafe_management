package com.springboot.springboot.service;

import com.springboot.springboot.entity.User;
import com.springboot.springboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        Map<String, List<String>> rolesAndPermissions = userRepository.findRolesAndPermissionsByUserId(user.getId());

        List<String> authorities = new ArrayList<>();

        // Thêm roles
        List<String> roles = rolesAndPermissions.getOrDefault("roles", List.of());
        roles.forEach(role -> authorities.add("ROLE_" + role));

        // Thêm permissions
        List<String> permissions = rolesAndPermissions.getOrDefault("role_permissions", List.of());
        authorities.addAll(permissions);

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                authorities.stream().map(SimpleGrantedAuthority::new).toList()
        );
    }
}
