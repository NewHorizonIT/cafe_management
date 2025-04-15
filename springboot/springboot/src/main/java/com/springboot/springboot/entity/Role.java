package com.springboot.springboot.entity;



import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Role {
    @Id
    int id;
    String name;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
    @ManyToMany
    Set<Permission> permissions;
}

