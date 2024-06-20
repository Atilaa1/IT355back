package org.example.it355pz.service;

import org.example.it355pz.model.MedicineEntity;

import java.util.List;

public interface MedicineService {
    List<MedicineEntity> getAllMedicines();
    MedicineEntity getMedicineById(int id);
    MedicineEntity saveMedicine(MedicineEntity medicine);
    void deleteMedicine(int id);
}
