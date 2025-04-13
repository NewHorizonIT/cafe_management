package com.springboot.springboot.repository;

import com.springboot.springboot.model.PurchaseDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PurchaseDetailRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private RowMapper<PurchaseDetail> purchaseDetailRowMapper = (rs, rowNum) -> {
        PurchaseDetail detail = new PurchaseDetail();
        detail.setMaterialId(rs.getInt("material_id"));
        detail.setPurchaseId(rs.getInt("purchase_id"));
        detail.setQuantity(rs.getInt("quantity"));
        detail.setPrice(rs.getInt("price"));
        detail.setTotal(rs.getInt("total"));
        return detail;
    };

    // Thêm chi tiết phiếu nhập kho
    public int save(PurchaseDetail detail) {
        String sql = "INSERT INTO purchase_detail (material_id, purchase_id, quantity, price, total) VALUES (?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, detail.getMaterialId(), detail.getPurchaseId(), detail.getQuantity(), detail.getPrice(), detail.getTotal());
    }

    // Cập nhật chi tiết phiếu nhập kho
    public int update(PurchaseDetail detail) {
        String sql = "UPDATE purchase_detail SET quantity = ?, price = ?, total = ? WHERE material_id = ? AND purchase_id = ?";
        return jdbcTemplate.update(sql, detail.getQuantity(), detail.getPrice(), detail.getTotal(), detail.getMaterialId(), detail.getPurchaseId());
    }

    // Lấy danh sách tất cả chi tiết phiếu nhập kho
    public List<PurchaseDetail> findAll() {
        String sql = "SELECT * FROM purchase_detail";
        return jdbcTemplate.query(sql, purchaseDetailRowMapper);
    }

    // Lấy chi tiết phiếu nhập kho theo Purchase ID
    public List<PurchaseDetail> findByPurchaseId(int purchaseId) {
        String sql = "SELECT * FROM purchase_detail WHERE purchase_id = ?";
        return jdbcTemplate.query(sql, purchaseDetailRowMapper, purchaseId);
    }

    // Xóa chi tiết phiếu nhập kho theo Purchase ID và Material ID
    public int delete(int materialId, int purchaseId) {
        String sql = "DELETE FROM purchase_detail WHERE material_id = ? AND purchase_id = ?";
        return jdbcTemplate.update(sql, materialId, purchaseId);
    }

    // Xóa theo purchaseId (nếu cần)
    public int deleteByPurchaseId(int purchaseId) {
        String sql = "DELETE FROM purchase_detail WHERE purchase_id = ?";
        return jdbcTemplate.update(sql, purchaseId);
    }
}
