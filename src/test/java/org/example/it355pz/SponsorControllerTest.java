package org.example.it355pz;


import org.example.it355pz.controller.SponsorController;
import org.example.it355pz.model.SponsorsEntity;
import org.example.it355pz.service.SponsorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class SponsorControllerTest {

    private MockMvc mockMvc;

    @Mock
    private SponsorService sponsorService;

    @InjectMocks
    private SponsorController sponsorController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(sponsorController).build();
    }

    @Test
    void getAllSponsors() throws Exception {
        SponsorsEntity sponsor1 = new SponsorsEntity("img1.jpg", "http://link1.com");
        SponsorsEntity sponsor2 = new SponsorsEntity("img2.jpg", "http://link2.com");
        sponsor1.setId(1);
        sponsor2.setId(2);
        List<SponsorsEntity> sponsorsList = Arrays.asList(sponsor1, sponsor2);

        when(sponsorService.getAllSponsors()).thenReturn(sponsorsList);

        mockMvc.perform(get("/api/sponsors"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].img").value("img1.jpg"))
                .andExpect(jsonPath("$[0].link").value("http://link1.com"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].img").value("img2.jpg"))
                .andExpect(jsonPath("$[1].link").value("http://link2.com"));

        verify(sponsorService, times(1)).getAllSponsors();
    }

    @Test
    void getSponsorById() throws Exception {
        SponsorsEntity sponsor = new SponsorsEntity("img1.jpg", "http://link1.com");
        sponsor.setId(1);

        when(sponsorService.getSponsorById(1)).thenReturn(sponsor);

        mockMvc.perform(get("/api/sponsors/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.img").value("img1.jpg"))
                .andExpect(jsonPath("$.link").value("http://link1.com"));

        verify(sponsorService, times(1)).getSponsorById(1);
    }

    @Test
    void createSponsor() throws Exception {
        SponsorsEntity sponsor = new SponsorsEntity("img1.jpg", "http://link1.com");
        sponsor.setId(1);

        when(sponsorService.saveSponsor(any(SponsorsEntity.class))).thenReturn(sponsor);

        mockMvc.perform(post("/api/sponsors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"img\":\"img1.jpg\", \"link\":\"http://link1.com\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.img").value("img1.jpg"))
                .andExpect(jsonPath("$.link").value("http://link1.com"));

        verify(sponsorService, times(1)).saveSponsor(any(SponsorsEntity.class));
    }

    @Test
    void updateSponsor() throws Exception {
        SponsorsEntity sponsor = new SponsorsEntity("updatedImg.jpg", "http://updatedLink.com");
        sponsor.setId(1);

        when(sponsorService.saveSponsor(any(SponsorsEntity.class))).thenReturn(sponsor);

        mockMvc.perform(put("/api/sponsors/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"img\":\"updatedImg.jpg\", \"link\":\"http://updatedLink.com\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.img").value("updatedImg.jpg"))
                .andExpect(jsonPath("$.link").value("http://updatedLink.com"));

        verify(sponsorService, times(1)).saveSponsor(any(SponsorsEntity.class));
    }

    @Test
    void deleteSponsor() throws Exception {
        doNothing().when(sponsorService).deleteSponsor(1);

        mockMvc.perform(delete("/api/sponsors/1"))
                .andExpect(status().isOk());

        verify(sponsorService, times(1)).deleteSponsor(1);
    }
}
