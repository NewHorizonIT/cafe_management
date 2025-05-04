package com.springboot.springboot.dto.request;

import com.springboot.springboot.entity.OrderDetail;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {
    private int userId;
    private int total;
    private String status;
    private OrderDetail[] orderDetails;
}
