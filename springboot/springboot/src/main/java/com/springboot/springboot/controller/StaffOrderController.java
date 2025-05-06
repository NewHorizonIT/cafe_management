package com.springboot.springboot.controller;

import com.springboot.springboot.model.Invoice;
import com.springboot.springboot.model.Order;
import com.springboot.springboot.model.OrderDetail;
import com.springboot.springboot.repository.InvoiceRepository;
import com.springboot.springboot.service.StaffOrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/staff/orders")
public class StaffOrderController {

    @Autowired
    private StaffOrderService staffOrderService;

    // 1. Duyệt đơn hàng
    @PutMapping("/{orderId}/approve")
    public Order approveOrder(@PathVariable int orderId) {
        return staffOrderService.approveOrder(orderId);
    }

    // 2. Hủy đơn hàng
    @PutMapping("/{orderId}/cancel")
    public Order cancelOrder(@PathVariable int orderId) {
        return staffOrderService.cancelOrder(orderId);
    }

    // 3. In hóa đơn (xem chi tiết đơn hàng)
    @GetMapping("/{orderId}/invoice")
    public List<OrderDetail> printInvoice(@PathVariable int orderId) {
        return staffOrderService.getOrderDetails(orderId);
    }

    // 4. Tạo hóa đơn cho đơn hàng
    @PostMapping("/{orderId}/invoice")
    public void createInvoice(@PathVariable int orderId) {
        staffOrderService.createInvoice(orderId);
    }

    // 5. Lấy danh sách hóa đơn theo orderId
    @GetMapping("/{orderId}/invoices")
    public List<Invoice> getInvoicesByOrderId(@PathVariable int orderId) {
        return staffOrderService.getInvoicesByOrderId(orderId);
    }

    // 6. Cập nhật hóa đơn theo invoiceId
    @PutMapping("/invoices/{invoiceId}")
    public void updateInvoice(@PathVariable int invoiceId, @RequestParam int newOrderId) {
        staffOrderService.updateInvoice(invoiceId, newOrderId);
    }

    // 7. Xóa hóa đơn theo orderId
    @DeleteMapping("/{orderId}/invoices")
    public void deleteInvoicesByOrderId(@PathVariable int orderId) {
        staffOrderService.deleteInvoicesByOrderId(orderId);
    }

    // 8. Lấy tất cả hóa đơn
    @GetMapping("/invoices")
    public List<Invoice> getAllInvoices() {
        return staffOrderService.getAllInvoices();
    }
}
