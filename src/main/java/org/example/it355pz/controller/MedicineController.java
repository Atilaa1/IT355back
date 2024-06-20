package org.example.it355pz.controller;

import org.example.it355pz.model.MedicineEntity;
import org.example.it355pz.service.MedicineService;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medicines")
@CrossOrigin(origins = "http://localhost:4200")
public class MedicineController {
    @Autowired
    private MedicineService medicineService;

    @GetMapping
    public List<MedicineEntity> getAllMedicines() {
        return medicineService.getAllMedicines();
    }

    @GetMapping("/{id}")
    public MedicineEntity getMedicineById(@PathVariable int id) {
        return medicineService.getMedicineById(id);
    }

    @PostMapping
   // @PreAuthorize("hasRole('ROLE_ADMIN')")
    public MedicineEntity createMedicine(@RequestBody MedicineEntity medicine) {
        return medicineService.saveMedicine(medicine);
    }

    @PutMapping("/{id}")
   // @PreAuthorize("hasRole('ROLE_ADMIN')")
    public MedicineEntity updateMedicine(@PathVariable int id, @RequestBody MedicineEntity medicine) {
        medicine.setId(id);
        return medicineService.saveMedicine(medicine);
    }

    @DeleteMapping("/{id}")
    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteMedicine(@PathVariable int id) {
        medicineService.deleteMedicine(id);
    }
}
