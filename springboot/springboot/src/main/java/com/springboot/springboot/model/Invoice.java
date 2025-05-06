package com.springboot.springboot.model;

import lombok.*;
import java.sql.Timestamp;


@Data
@NoArgsConstructor
@AllArgsConstructor

public class Invoice {
    private int invoiceId;
    private int orderId;
    private Timestamp createdAt;
}