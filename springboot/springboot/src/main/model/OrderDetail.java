package com.springboot.springboot.model;

public class OrderDetail {
    private int drinkId;
    private int orderId;
    private int quantity;
    private double price;
    private double total;  // Tính toán tổng cho mỗi sản phẩm trong đơn hàng

    // Getters and Setters
    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    // Các getter và setter khác
}
