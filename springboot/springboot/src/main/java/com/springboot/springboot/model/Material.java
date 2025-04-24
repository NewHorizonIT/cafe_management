package com.springboot.springboot.model;

import lombok.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Material {
    private int id;
    private String name;
    private int quantity;
    private int status;
    private LocalDateTime dateEntry;
    private int supplierId;
}
