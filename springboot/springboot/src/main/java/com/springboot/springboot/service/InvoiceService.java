package com.springboot.springboot.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.springboot.model.Invoice;
import com.springboot.springboot.repository.InvoiceRepository;

@Service
public class InvoiceService {
    @Autowired
    private InvoiceRepository invoiceRepository;

    // Lấy danh sách tất cả hóa đơn
    public List<Invoice> getAllInvoices() {
        return invoiceRepository.getAllInvoices();
    }

    // Lấy hóa đơn theo ID
    public Invoice getInvoiceById(int invoiceID) {
        return invoiceRepository.getInvoiceById(invoiceID);
    }

    // Thêm hóa đơn mới
    public void createInvoice(Invoice invoice) {
        invoiceRepository.createInvoice(invoice);
    }

    // Cập nhật hóa đơn
    public void updateInvoice(Invoice invoice) {
        invoiceRepository.updateInvoice(invoice);
    }

    // Xóa hóa đơn
    public void deleteInvoice(int invoiceID) {
        invoiceRepository.deleteInvoice(invoiceID);
    }

    // Lấy hóa đơn theo orderID
    public List<Invoice> getInvoicesByOrderId(int orderID) {
        return invoiceRepository.getInvoicesByOrderId(orderID);
    }

    // Lấy hóa đơn theo payment
    public List<Invoice> getInvoicesByPayment(String payment) {
        return invoiceRepository.getInvoicesByPayment(payment);
    }

    // Lấy hóa đơn theo ngày tạo
    public List<Invoice> getInvoicesByCreatedAt(LocalDate createdAt) {
        return invoiceRepository.getInvoicesByDate(createdAt);
    }

    // Lấy hóa đơn theo khoảng thời gian
    public List<Invoice> getInvoicesByDateRange(LocalDate startDate, LocalDate endDate) {
        return invoiceRepository.getInvoicesByDateRange(startDate, endDate);
    }

}
