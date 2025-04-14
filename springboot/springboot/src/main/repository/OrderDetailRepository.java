package com.springboot.springboot.repository;

import com.springboot.springboot.model.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer> {
    List<OrderDetail> findByOrderId(int orderId);
}
