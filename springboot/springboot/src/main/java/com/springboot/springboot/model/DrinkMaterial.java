
package com.springboot.springboot.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DrinkMaterial {
    private int drinkId;
    private int materialId;
    private int quantity;
}