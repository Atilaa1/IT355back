package org.example.it355pz.controller;

import org.example.it355pz.model.DoctorsEntity;
import org.example.it355pz.service.DoctorsService;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctors")
@CrossOrigin(origins = "http://localhost:4200")
public class DoctorsController {
    @Autowired
    private DoctorsService doctorsService;

    @GetMapping
    public List<DoctorsEntity> getAllDoctors() {
        return doctorsService.getAllDoctors();
    }

    @GetMapping("/{id}")
    public DoctorsEntity getDoctorById(@PathVariable int id) {
        return doctorsService.getDoctorById(id);
    }

    @PostMapping
  @PreAuthorize("hasRole('ROLE_ADMIN')")
    public DoctorsEntity createDoctor(@RequestBody DoctorsEntity doctor) {
        return doctorsService.saveDoctor(doctor);
    }

    @PutMapping("/{id}")
 @PreAuthorize("hasRole('ROLE_ADMIN')")
    public DoctorsEntity updateDoctor(@PathVariable int id, @RequestBody DoctorsEntity doctor) {
        doctor.setId(id);
        return doctorsService.saveDoctor(doctor);
    }

    @DeleteMapping("/{id}")
@PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteDoctor(@PathVariable int id) {
        doctorsService.deleteDoctor(id);
    }
}
