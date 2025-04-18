package com.springboot.springboot.model;

import lombok.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Supplier {
    private int id;               // Mã nhà cung cấp
    private String supplier;      // Tên nhà cung cấp
    private LocalDateTime createdAt;  // Ngày tạo
}
