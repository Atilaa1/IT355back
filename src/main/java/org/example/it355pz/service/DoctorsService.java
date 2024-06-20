package org.example.it355pz.service;

import org.example.it355pz.model.DoctorsEntity;

import java.util.List;

public interface DoctorsService {
    List<DoctorsEntity> getAllDoctors();
    DoctorsEntity getDoctorById(int id);
    DoctorsEntity saveDoctor(DoctorsEntity doctor);
    void deleteDoctor(int id);
}
