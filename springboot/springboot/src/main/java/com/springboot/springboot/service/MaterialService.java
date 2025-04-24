package com.springboot.springboot.service;

import com.springboot.springboot.model.Material;
import com.springboot.springboot.repository.MaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaterialService {

    @Autowired
    private MaterialRepository materialRepository;

    public int createMaterial(Material material) {
        return materialRepository.save(material);
    }

    public int updateMaterial(Material material) {
        return materialRepository.update(material);
    }

    public int deleteMaterial(int id) {
        return materialRepository.delete(id);
    }

    public List<Material> getAllMaterials() {
        return materialRepository.findAll();
    }

    public Material getMaterialById(int id) {
        return materialRepository.findById(id);
    }
}
