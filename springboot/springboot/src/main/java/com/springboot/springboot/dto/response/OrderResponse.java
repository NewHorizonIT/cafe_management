package com.springboot.springboot.dto.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor

public class OrderResponse {
    private int id;
    private int userId;
    private int total;
    private String status;
    private String createdAt;
    private List<OrderDetailResponse> orderDetails;

    // Các trường khác nếu cần
}
