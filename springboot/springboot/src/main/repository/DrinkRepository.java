package com.springboot.springboot.repository;

import com.springboot.springboot.model.Drink;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DrinkRepository extends JpaRepository<Drink, Integer> {
    List<Drink> findByNameContaining(String keyword);
    List<Drink> findByCategoryAndPriceBetween(String category, double minPrice, double maxPrice);
}
