package com.springboot.springboot.entity;

import lombok.*;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generates the primary key
    @Column(name = "id") // Maps the purchaseId field to the 'purchase_id' column in the table
    private int purchaseId; // Mã phiếu nhập kho

    @Column(name = "date") // Maps the 'date' field to the 'date' column
    private LocalDateTime date; // Ngày tạo phiếu nhập

    @Column(name = "total") // Maps the 'total' field to the 'total' column
    private int total; // Tổng tiền của phiếu nhập kho

    @Column(name = "status") // Maps the 'status' field to the 'status' column
    private String status; // Trạng thái phiếu nhập (1 Done | 0 In process)

    @Column(name = "created_at") // Maps the 'createdAt' field to the 'created_at' column
    private LocalDateTime createdAt; // Ngày tạo

    @Column(name = "updated_at") // Maps the 'updatedAt' field to the 'updated_at' column
    private LocalDateTime updatedAt; // Ngày cập nhật

    @Column(name = "supplier_id") // Maps the 'supplierId' field to the 'supplier_id' column
    private int supplierId; // ID nhà cung cấp

    @Column(name = "staff_id") // Maps the 'staffId' field to the 'staff_id' column
    private int staffId; // ID nhân viên lập phiếu nhập kho
}
