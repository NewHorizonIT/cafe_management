package com.springboot.springboot.model;

import lombok.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Purchase {
    private int purchaseId; // Mã phiếu nhập kho
    private LocalDateTime date; // Ngày tạo phiếu nhập
    private int total; // Tổng tiền của phiếu nhập kho
    private String status; // Trạng thái phiếu nhập (1 Done | 0 In process)
    private LocalDateTime createdAt; // Ngày tạo
    private LocalDateTime updatedAt; // Ngày cập nhật
    private int supplierId; // ID nhà cung cấp
    private int staffId; // ID nhân viên lập phiếu nhập kho
}
