package org.example.it355pz.repository;

import org.example.it355pz.model.HospitalEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HospitalRepository extends JpaRepository<HospitalEntity, Integer> {
}
