package org.example.it355pz.service.impl;

import org.example.it355pz.model.HospitalEntity;
import org.example.it355pz.repository.HospitalRepository;
import org.example.it355pz.service.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HospitalServiceImpl implements HospitalService {
    @Autowired
    private HospitalRepository hospitalRepository;

    @Override
    public List<HospitalEntity> getAllHospitals() {
        return hospitalRepository.findAll();
    }

    @Override
    public HospitalEntity getHospitalById(int id) {
        return hospitalRepository.findById(id).orElse(null);
    }

    @Override
    public HospitalEntity saveHospital(HospitalEntity hospital) {
        return hospitalRepository.save(hospital);
    }

    @Override
    public void deleteHospital(int id) {
        hospitalRepository.deleteById(id);
    }
}
