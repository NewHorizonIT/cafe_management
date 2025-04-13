// MaterialRepository
package com.springboot.springboot.repository;

// Ensure the correct package path for Material
import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.springboot.model.Material;

public interface MaterialRepository extends JpaRepository<Material, Integer> {}
