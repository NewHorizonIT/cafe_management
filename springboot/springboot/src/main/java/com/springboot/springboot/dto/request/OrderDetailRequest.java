package com.springboot.springboot.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailRequest {
    private int drinkId; // ID của sản phẩm
    private int orderId; // ID của đơn hàng
    private int quantity; // Số lượng sản phẩm
    private int price; // Giá sản phẩm
    private int total; // Tổng tiền cho sản phẩm này
}
