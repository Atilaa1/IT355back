package org.example.it355pz.service;

import org.example.it355pz.model.HospitalEntity;

import java.util.List;

public interface HospitalService {
    List<HospitalEntity> getAllHospitals();
    HospitalEntity getHospitalById(int id);
    HospitalEntity saveHospital(HospitalEntity hospital);
    void deleteHospital(int id);
}
