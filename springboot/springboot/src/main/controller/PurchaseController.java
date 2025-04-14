package com.springboot.springboot.controller;

import com.springboot.springboot.model.Order;
import com.springboot.springboot.model.OrderDetail;
import com.springboot.springboot.service.OrderService;
import com.springboot.springboot.service.DrinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class PurchaseController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private DrinkService drinkService;

    @PostMapping("/api/purchase")
    public Order placeOrder(@RequestBody Order order) {
        return orderService.placeOrder(order);
    }
}
