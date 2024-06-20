package org.example.it355pz.repository;

import org.example.it355pz.model.MedicineEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicineRepository extends JpaRepository<MedicineEntity, Integer> {
}
