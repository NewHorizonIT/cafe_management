package com.springboot.springboot.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailResponse {
    private int drinkId; // ID của sản phẩm
    private int orderId; // ID của đơn hàng
    private int quantity; // Số lượng sản phẩm
    private double price; // Giá sản phẩm
    private double total; // Tổng tiền cho sản phẩm này

}
