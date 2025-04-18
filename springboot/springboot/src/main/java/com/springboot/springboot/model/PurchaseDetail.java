package com.springboot.springboot.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class PurchaseDetail {
    private int materialId;   // ID của vật liệu trong phiếu nhập
    private int purchaseId;   // ID của phiếu nhập kho
    private int quantity;     // Số lượng vật liệu nhập vào kho
    private int price;        // Giá nhập của mỗi vật liệu
    private int total;        // Tổng tiền (price * quantity)
}
