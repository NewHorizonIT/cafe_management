package com.springboot.springboot.service;

import com.springboot.springboot.model.Supplier;
import com.springboot.springboot.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SupplierService {

    @Autowired
    private SupplierRepository supplierRepository;

    // Lấy danh sách nhà cung cấp
    public List<Supplier> getAllSuppliers() {
        return supplierRepository.findAll();
    }

    // Lấy nhà cung cấp theo ID
    public Supplier getSupplierById(int id) {
        return supplierRepository.findById(id);
    }

    // Thêm nhà cung cấp mới
    public int createSupplier(Supplier supplier) {
        return supplierRepository.save(supplier);
    }

    // Cập nhật nhà cung cấp
    public int updateSupplier(Supplier supplier) {
        return supplierRepository.update(supplier);
    }

    // Xóa nhà cung cấp
    public int deleteSupplier(int id) {
        return supplierRepository.delete(id);
    }
}
