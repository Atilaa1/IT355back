package org.example.it355pz.controller;

import org.example.it355pz.model.HospitalEntity;
import org.example.it355pz.service.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hospitals")
@CrossOrigin(origins = "http://localhost:4200")
public class HospitalController {
    @Autowired
    private HospitalService hospitalService;

    @GetMapping
    public List<HospitalEntity> getAllHospitals() {
        return hospitalService.getAllHospitals();
    }

    @GetMapping("/{id}")
    public HospitalEntity getHospitalById(@PathVariable int id) {
        return hospitalService.getHospitalById(id);
    }

    @PostMapping
   // @PreAuthorize("hasRole('ROLE_ADMIN')")
    public HospitalEntity createHospital(@RequestBody HospitalEntity hospital) {
        return hospitalService.saveHospital(hospital);
    }

    @PutMapping("/{id}")
    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    public HospitalEntity updateHospital(@PathVariable int id, @RequestBody HospitalEntity hospital) {
        hospital.setId(id);
        return hospitalService.saveHospital(hospital);
    }

    @DeleteMapping("/{id}")
   // @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteHospital(@PathVariable int id) {
        hospitalService.deleteHospital(id);
    }
}
