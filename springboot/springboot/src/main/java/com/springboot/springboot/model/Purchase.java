package com.springboot.springboot.model;

import java.time.LocalDateTime;

public class Purchase {
    private int purchaseId;
    private LocalDateTime date;
    private int total;
    private int status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private int supplierId;
    private int staffId;
}
