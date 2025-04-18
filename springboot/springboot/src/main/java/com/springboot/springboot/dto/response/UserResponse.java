package com.springboot.springboot.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponse {
    int id;
    String username;
    String email;
    String phone;
    String status;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
    Set<String> roles;
}