// OrderRepository.java
package com.springboot.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.springboot.model.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {}
