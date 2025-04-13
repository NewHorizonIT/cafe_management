package com.springboot.springboot.model;

import lombok.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Drink {
    private int id;
    private String name;         // Tên sản phẩm
    private String thumbnail;    // Link ảnh sản phẩm
    private String description;  // Mô tả sản phẩm
    private String price;    // Giá tiền
    private int stock;           // Số lượng tồn kho
    private int categoryId;      // Loại sản phẩm
    private LocalDateTime createdAt;  // Ngày tạo
    private LocalDateTime updatedAt;  // Ngày cập nhật
}
