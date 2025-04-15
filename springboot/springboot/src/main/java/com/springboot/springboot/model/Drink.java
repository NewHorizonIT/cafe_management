package com.springboot.springboot.model;

import java.time.LocalDateTime;

public class Drink {
    private int id;
    private String name;
    private String thumbnail;
    private String description;
    private String price;
    private int stock;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private int categoryId;
}
