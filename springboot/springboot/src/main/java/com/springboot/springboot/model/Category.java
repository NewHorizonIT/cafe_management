package com.springboot.springboot.model;

import lombok.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Category {
    private int id;              // Mã danh mục sản phẩm
    private String name;         // Tên danh mục
    private LocalDateTime createdAt;  // Thời gian tạo danh mục
}
