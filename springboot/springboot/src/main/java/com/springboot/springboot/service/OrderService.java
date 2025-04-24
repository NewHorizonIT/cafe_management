package com.springboot.springboot.service;

import com.springboot.springboot.model.Order;
import com.springboot.springboot.model.OrderDetail;
import com.springboot.springboot.model.Drink;
import com.springboot.springboot.repository.OrderRepository;
import com.springboot.springboot.repository.OrderDetailRepository;
import com.springboot.springboot.repository.DrinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private DrinkRepository drinkRepository;

    public Order placeOrder(Order order) {
        double total = 0;

        // Duyệt qua các chi tiết đơn hàng để tính tổng giá trị
        for (OrderDetail orderDetail : order.getOrderDetails()) {
            // Lấy sản phẩm tương ứng từ database
            Drink drink = drinkRepository.findById(orderDetail.getDrinkId());

            if (drink == null) {
                throw new RuntimeException("Drink not found with id: " + orderDetail.getDrinkId());
            }

            // Tính toán giá trị cho mỗi chi tiết đơn hàng
            int orderItemTotal = drink.getPrice() * orderDetail.getQuantity();
            orderDetail.setTotal(orderItemTotal);
            total += orderItemTotal;

            // Cập nhật số lượng sản phẩm
            drink.setStock(drink.getStock() - orderDetail.getQuantity());
            drinkRepository.save(drink);
        }

        // Cập nhật tổng giá trị cho đơn hàng
        order.setTotal((int) total);

        // Lưu đơn hàng và các chi tiết đơn hàng
        order = orderRepository.save(order);

        // Lưu các chi tiết đơn hàng vào cơ sở dữ liệu
        for (OrderDetail orderDetail : order.getOrderDetails()) {
            orderDetail.setOrderId(order.getId());
            orderDetailRepository.saveOrderDetail(orderDetail);
            ;
        }

        return order;
    }
}
