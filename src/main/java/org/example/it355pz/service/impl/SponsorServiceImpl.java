package org.example.it355pz.service.impl;

import org.example.it355pz.model.SponsorsEntity;
import org.example.it355pz.repository.SponsorsRepository;
import org.example.it355pz.service.SponsorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SponsorServiceImpl implements SponsorService {
    @Autowired
    private SponsorsRepository sponsorsRepository;

    @Override
    public List<SponsorsEntity> getAllSponsors() {
        return sponsorsRepository.findAll();
    }

    @Override
    public SponsorsEntity getSponsorById(int id) {
        return sponsorsRepository.findById(id).orElse(null);
    }

    @Override
    public SponsorsEntity saveSponsor(SponsorsEntity sponsor) {
        return sponsorsRepository.save(sponsor);
    }

    @Override
    public void deleteSponsor(int id) {
        sponsorsRepository.deleteById(id);
    }
}
