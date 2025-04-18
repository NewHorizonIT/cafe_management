package com.springboot.springboot.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetail {
    private int drinkId;
    private int orderId;
    private int quantity;
    private int total;
    private int price;
}
