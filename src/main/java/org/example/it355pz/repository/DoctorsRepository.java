package org.example.it355pz.repository;

import org.example.it355pz.model.DoctorsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorsRepository extends JpaRepository<DoctorsEntity, Integer> {
}
