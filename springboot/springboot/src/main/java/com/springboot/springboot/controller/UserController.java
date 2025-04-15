package com.springboot.springboot.controller;

import com.springboot.springboot.dto.request.UserUpdateMeRequest;
import com.springboot.springboot.dto.request.UserUpdateRequest;
import com.springboot.springboot.dto.response.ApiResponse;
import com.springboot.springboot.dto.request.UserCreationRequest;
import com.springboot.springboot.dto.response.UserResponse;
import com.springboot.springboot.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/db_cafe_management/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Public registration endpoint
    @PostMapping("/register")
    public ResponseEntity<ApiResponse<UserResponse>> registerUser(@Valid @RequestBody UserCreationRequest request) {
        UserResponse userResponse = userService.createUser(request);
        return ResponseEntity.ok(ApiResponse.<UserResponse>builder()
                .code("200")
                .message("Success")
                .result(userResponse)
                .build());
    }

    // Get current user
    @GetMapping("/me")
    public ResponseEntity<ApiResponse<UserResponse>> getCurrentUser() {
        UserResponse userResponse = userService.getCurrentUser();
        return ResponseEntity.ok(ApiResponse.<UserResponse>builder()
                .code("200")
                .message("Success")
                .result(userResponse)
                .build());
    }

    // Update current user
    @PutMapping("/me")
    public ResponseEntity<ApiResponse<UserResponse>> updateCurrentUser(@Valid @RequestBody UserUpdateMeRequest request) {
        UserResponse userResponse = userService.updateCurrentUser(request);
        return ResponseEntity.ok(ApiResponse.<UserResponse>builder()
                .code("200")
                .message("Success")
                .result(userResponse)
                .build());
    }

    // Create (Admin only)
    @PostMapping
    public ResponseEntity<ApiResponse<UserResponse>> createUser(@Valid @RequestBody UserCreationRequest request) {
        UserResponse userResponse = userService.createUser(request);
        return ResponseEntity.ok(ApiResponse.<UserResponse>builder()
                .code("200")
                .message("Success")
                .result(userResponse)
                .build());
    }

    // Read (lấy tất cả người dùng, Admin only)
    @GetMapping
    public ResponseEntity<ApiResponse<List<UserResponse>>> getAllUsers() {
        List<UserResponse> users = userService.getAllUsers();
        return ResponseEntity.ok(ApiResponse.<List<UserResponse>>builder()
                .code("200")
                .message("Success")
                .result(users)
                .build());
    }

    // Read (lấy người dùng theo ID, Admin only)
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<UserResponse>> getUserById(@PathVariable int id) {
        UserResponse userResponse = userService.getUserById(id);
        return ResponseEntity.ok(ApiResponse.<UserResponse>builder()
                .code("200")
                .message("Success")
                .result(userResponse)
                .build());
    }

    // Update (Admin only)
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<UserResponse>> updateUser(@PathVariable int id, @Valid @RequestBody UserUpdateRequest request) {
        UserResponse userResponse = userService.updateUser(id, request);
        return ResponseEntity.ok(ApiResponse.<UserResponse>builder()
                .code("200")
                .message("Success")
                .result(userResponse)
                .build());
    }

    // Delete (Admin only)
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
        return ResponseEntity.ok(ApiResponse.<Void>builder()
                .code("200")
                .message("Success")
                .build());
    }

    // Gán role cho user (Admin only)
    @PostMapping("/{id}/roles/{roleId}")
    public ResponseEntity<ApiResponse<Void>> assignRole(@PathVariable("id") int userId, @PathVariable int roleId) {
        userService.assignRole(userId, roleId);
        return ResponseEntity.ok(ApiResponse.<Void>builder()
                .code("200")
                .message("Success")
                .build());
    }

    // Xóa role của user (Admin only)
    @DeleteMapping("/{id}/roles/{roleId}")
    public ResponseEntity<ApiResponse<Void>> removeRole(@PathVariable("id") int userId, @PathVariable int roleId) {
        userService.removeRole(userId, roleId);
        return ResponseEntity.ok(ApiResponse.<Void>builder()
                .code("200")
                .message("Success")
                .build());
    }

    // Lấy danh sách role của user (Admin only)
    @GetMapping("/{id}/roles")
    public ResponseEntity<ApiResponse<List<Integer>>> getRoleIdsByUserId(@PathVariable("id") int userId) {
        List<Integer> roleIds = userService.getRoleIdsByUserId(userId);
        return ResponseEntity.ok(ApiResponse.<List<Integer>>builder()
                .code("200")
                .message("Success")
                .result(roleIds)
                .build());
    }
}

