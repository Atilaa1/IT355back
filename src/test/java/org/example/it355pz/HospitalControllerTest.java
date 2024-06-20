package org.example.it355pz;


import org.example.it355pz.controller.HospitalController;
import org.example.it355pz.model.HospitalEntity;
import org.example.it355pz.service.HospitalService;
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

class HospitalControllerTest {

    private MockMvc mockMvc;

    @Mock
    private HospitalService hospitalService;

    @InjectMocks
    private HospitalController hospitalController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(hospitalController).build();
    }

    @Test
    void getAllHospitals() throws Exception {
        HospitalEntity hospital1 = new HospitalEntity("Hospital A", "Location A", "imgA.jpg", 100);
        HospitalEntity hospital2 = new HospitalEntity("Hospital B", "Location B", "imgB.jpg", 200);
        hospital1.setId(1);
        hospital2.setId(2);
        List<HospitalEntity> hospitalsList = Arrays.asList(hospital1, hospital2);

        when(hospitalService.getAllHospitals()).thenReturn(hospitalsList);

        mockMvc.perform(get("/hospitals"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("Hospital A"))
                .andExpect(jsonPath("$[0].location").value("Location A"))
                .andExpect(jsonPath("$[0].img").value("imgA.jpg"))
                .andExpect(jsonPath("$[0].capacity").value(100))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].name").value("Hospital B"))
                .andExpect(jsonPath("$[1].location").value("Location B"))
                .andExpect(jsonPath("$[1].img").value("imgB.jpg"))
                .andExpect(jsonPath("$[1].capacity").value(200));

        verify(hospitalService, times(1)).getAllHospitals();
    }

    @Test
    void getHospitalById() throws Exception {
        HospitalEntity hospital = new HospitalEntity("Hospital A", "Location A", "imgA.jpg", 100);
        hospital.setId(1);

        when(hospitalService.getHospitalById(1)).thenReturn(hospital);

        mockMvc.perform(get("/hospitals/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Hospital A"))
                .andExpect(jsonPath("$.location").value("Location A"))
                .andExpect(jsonPath("$.img").value("imgA.jpg"))
                .andExpect(jsonPath("$.capacity").value(100));

        verify(hospitalService, times(1)).getHospitalById(1);
    }

    @Test
    void createHospital() throws Exception {
        HospitalEntity hospital = new HospitalEntity("Hospital A", "Location A", "imgA.jpg", 100);
        hospital.setId(1);

        when(hospitalService.saveHospital(any(HospitalEntity.class))).thenReturn(hospital);

        mockMvc.perform(post("/hospitals")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Hospital A\", \"location\":\"Location A\", \"img\":\"imgA.jpg\", \"capacity\":100}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Hospital A"))
                .andExpect(jsonPath("$.location").value("Location A"))
                .andExpect(jsonPath("$.img").value("imgA.jpg"))
                .andExpect(jsonPath("$.capacity").value(100));

        verify(hospitalService, times(1)).saveHospital(any(HospitalEntity.class));
    }

    @Test
    void updateHospital() throws Exception {
        HospitalEntity hospital = new HospitalEntity("Updated Hospital A", "Updated Location A", "updatedImgA.jpg", 150);
        hospital.setId(1);

        when(hospitalService.saveHospital(any(HospitalEntity.class))).thenReturn(hospital);

        mockMvc.perform(put("/hospitals/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Updated Hospital A\", \"location\":\"Updated Location A\", \"img\":\"updatedImgA.jpg\", \"capacity\":150}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Updated Hospital A"))
                .andExpect(jsonPath("$.location").value("Updated Location A"))
                .andExpect(jsonPath("$.img").value("updatedImgA.jpg"))
                .andExpect(jsonPath("$.capacity").value(150));

        verify(hospitalService, times(1)).saveHospital(any(HospitalEntity.class));
    }

    @Test
    void deleteHospital() throws Exception {
        doNothing().when(hospitalService).deleteHospital(1);

        mockMvc.perform(delete("/hospitals/1"))
                .andExpect(status().isOk());

        verify(hospitalService, times(1)).deleteHospital(1);
    }
}