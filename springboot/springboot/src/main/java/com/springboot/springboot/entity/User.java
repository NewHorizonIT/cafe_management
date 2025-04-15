package com.springboot.springboot.entity;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String username;
    String email;
    String phone;
    String password;
    String status;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;

    @ManyToMany
    Set<Role> roles;
}


