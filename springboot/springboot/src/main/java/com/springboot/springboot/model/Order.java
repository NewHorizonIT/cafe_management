package com.springboot.springboot.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Order {
    private int id;
    private int status;
    private LocalDateTime createdAt;
    private int total;
    private int userId;
    private int staffId;

    public OrderDetail[] getOrderDetails() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getOrderDetails'");
    }

}
