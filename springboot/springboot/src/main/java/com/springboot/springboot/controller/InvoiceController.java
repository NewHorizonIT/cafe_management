package com.springboot.springboot.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.springboot.model.Invoice;
import com.springboot.springboot.service.InvoiceService;

@RestController
@RequestMapping("/api/v1/invoices")
public class InvoiceController {
    @Autowired
    private InvoiceService invoiceService;

    // Lấy danh sách tất cả hóa đơn
    @GetMapping
    public List<Invoice> getAllInvoices() {
        return invoiceService.getAllInvoices();
    }

    // Lấy hóa đơn theo ID
    @GetMapping("/{id}")
    public Invoice getInvoiceById(@PathVariable int id) {
        return invoiceService.getInvoiceById(id);
    }

    // Thêm hóa đơn mới
    @PostMapping
    public String createInvoice(@RequestBody Invoice invoice) {
        invoiceService.createInvoice(invoice);
        return "Thêm hóa đơn thành công";
    }

    // Cập nhật hóa đơn
    @PutMapping("/{id}")
    public String updateInvoice(@PathVariable int id, @RequestBody Invoice invoice) {
        invoice.setInvoiceID(id);
        invoiceService.updateInvoice(invoice);
        return "Cập nhật hóa đơn thành công";
    }

    // Xóa hóa đơn
    @DeleteMapping("/{id}")
    public String deleteInvoice(@PathVariable int id) {
        invoiceService.deleteInvoice(id);
        return "Xóa hóa đơn thành công";
    }

    // Lấy hóa đơn theo orderID
    @GetMapping("/order/{orderID}")
    public List<Invoice> getInvoicesByOrderId(@PathVariable int orderID) {
        return invoiceService.getInvoicesByOrderId(orderID);
    }

    // Lấy hóa đơn theo payment
    @GetMapping("/payment/{payment}")
    public List<Invoice> getInvoicesByPayment(@PathVariable String payment) {
        return invoiceService.getInvoicesByPayment(payment);
    }

    // Lấy hóa đơn theo ngày tạo
    @GetMapping("/createdAt/{createdAt}")
    public List<Invoice> getInvoicesByCreatedAt(@PathVariable LocalDate createdAt) {
        return invoiceService.getInvoicesByCreatedAt(createdAt);
    }

    // Lấy hóa đơn theo khoảng thời gian
    @GetMapping("/dateRange")
    public List<Invoice> getInvoicesByDateRange(@RequestParam LocalDate startDate, @RequestParam LocalDate endDate) {
        return invoiceService.getInvoicesByDateRange(startDate, endDate);
    }

    // Lấy hóa đơn theo phương thức thanh toán
    @GetMapping("/paymentMethod/{paymentMethod}")
    public List<Invoice> getInvoicesByPaymentMethod(@PathVariable String paymentMethod) {
        return invoiceService.getInvoicesByPayment(paymentMethod);
    }
}
