package com.springboot.springboot.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "drinks") // Tên bảng trong database
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Drink {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Tự động tăng ID
    private Integer id;

    @Column(nullable = false)
    private String name; // Tên sản phẩm

    private String thumbnail; // Link ảnh sản phẩm

    @Column(length = 1000)
    private String description; // Mô tả sản phẩm

    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    private Integer price; // Giá tiền

    @Column(name = "category_id")
    private Integer categoryId; // Loại sản phẩm

    @Column(name = "created_at")
    private LocalDateTime createdAt; // Ngày tạo

    @Column(name = "updated_at")
    private LocalDateTime updatedAt; // Ngày cập nhật
}
