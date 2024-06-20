package org.example.it355pz.service.impl;

import org.example.it355pz.model.MedicineEntity;
import org.example.it355pz.repository.MedicineRepository;
import org.example.it355pz.service.MedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicineServiceImpl implements MedicineService {
    @Autowired
    private MedicineRepository medicineRepository;

    @Override
    public List<MedicineEntity> getAllMedicines() {
        return medicineRepository.findAll();
    }

    @Override
    public MedicineEntity getMedicineById(int id) {
        return medicineRepository.findById(id).orElse(null);
    }

    @Override
    public MedicineEntity saveMedicine(MedicineEntity medicine) {
        return medicineRepository.save(medicine);
    }

    @Override
    public void deleteMedicine(int id) {
        medicineRepository.deleteById(id);
    }
}
