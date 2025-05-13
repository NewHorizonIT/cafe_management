package com.springboot.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.springboot.entity.Role;
import com.springboot.springboot.repository.RoleRepository;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;

    // lấy danh sách quyền
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    // thêm quyền
    public Role createRole(Role role) {
        Role newRole = roleRepository.save(role);
        return newRole;
    }

    // sửa quyền
    // xóa quyền
}
