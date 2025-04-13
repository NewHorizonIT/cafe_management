// PurchaseRepository
package com.springboot.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.springboot.model.Purchase;

public interface PurchaseRepository extends JpaRepository<Purchase, Integer> {}
