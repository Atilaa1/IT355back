package org.example.it355pz.controller;

import org.example.it355pz.model.SponsorsEntity;
import org.example.it355pz.service.SponsorService;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sponsors")
@CrossOrigin(origins = "http://localhost:4200")
public class SponsorController {
    @Autowired
    private SponsorService sponsorsService;

    @GetMapping
    public List<SponsorsEntity> getAllSponsors() {
        return sponsorsService.getAllSponsors();
    }

    @GetMapping("/{id}")
    public SponsorsEntity getSponsorById(@PathVariable int id) {
        return sponsorsService.getSponsorById(id);
    }

    @PostMapping
   // @PreAuthorize("hasRole('ROLE_ADMIN')")
    public SponsorsEntity createSponsor(@RequestBody SponsorsEntity sponsor) {
        return sponsorsService.saveSponsor(sponsor);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public SponsorsEntity updateSponsor(@PathVariable int id, @RequestBody SponsorsEntity sponsor) {
        sponsor.setId(id);
        return sponsorsService.saveSponsor(sponsor);
    }

    @DeleteMapping("/{id}")
   @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteSponsor(@PathVariable int id) {
        sponsorsService.deleteSponsor(id);
    }
}
