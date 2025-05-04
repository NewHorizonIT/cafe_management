package com.springboot.springboot.model;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Invoice {
    private int invoiceID;
    private int orderID;
    private String payment;
    private LocalDate createdAt;

}
