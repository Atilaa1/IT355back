package org.example.it355pz.service.impl;

import org.example.it355pz.model.DoctorsEntity;
import org.example.it355pz.repository.DoctorsRepository;
import org.example.it355pz.service.DoctorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorsServiceImpl implements DoctorsService {
    @Autowired
    private DoctorsRepository doctorsRepository;

    @Override
    public List<DoctorsEntity> getAllDoctors() {
        return doctorsRepository.findAll();
    }

    @Override
    public DoctorsEntity getDoctorById(int id) {
        return doctorsRepository.findById(id).orElse(null);
    }

    @Override
    public DoctorsEntity saveDoctor(DoctorsEntity doctor) {
        return doctorsRepository.save(doctor);
    }

    @Override
    public void deleteDoctor(int id) {
        doctorsRepository.deleteById(id);
    }
}
