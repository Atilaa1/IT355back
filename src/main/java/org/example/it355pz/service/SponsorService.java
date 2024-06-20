package org.example.it355pz.service;

import org.example.it355pz.model.SponsorsEntity;

import java.util.List;

public interface SponsorService {
    List<SponsorsEntity> getAllSponsors();
    SponsorsEntity getSponsorById(int id);
    SponsorsEntity saveSponsor(SponsorsEntity sponsor);
    void deleteSponsor(int id);
}
