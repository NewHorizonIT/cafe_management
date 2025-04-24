package com.springboot.springboot.service;

import com.springboot.springboot.model.PurchaseDetail;
import com.springboot.springboot.repository.PurchaseDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PurchaseDetailService {

    @Autowired
    private PurchaseDetailRepository purchaseDetailRepository;

    public List<PurchaseDetail> getAllPurchaseDetails() {
        return purchaseDetailRepository.findAll();
    }

    public List<PurchaseDetail> getDetailsByPurchaseId(int purchaseId) {
        return purchaseDetailRepository.findByPurchaseId(purchaseId);
    }

    public int createPurchaseDetail(PurchaseDetail purchaseDetail) {
        return purchaseDetailRepository.save(purchaseDetail);
    }

    public int updatePurchaseDetail(PurchaseDetail purchaseDetail) {
        return purchaseDetailRepository.update(purchaseDetail);
    }

    public int deletePurchaseDetail(int materialId, int purchaseId) {
        return purchaseDetailRepository.delete(materialId, purchaseId);
    }
}
