package org.example.it355pz;


import org.example.it355pz.controller.DoctorsController;
import org.example.it355pz.model.DoctorsEntity;
import org.example.it355pz.service.DoctorsService;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class DoctorsControllerTest {

    private MockMvc mockMvc;

    @Mock
    private DoctorsService doctorsService;

    @InjectMocks
    private DoctorsController doctorsController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(doctorsController).build();
    }

    @Test
    void getAllDoctors() throws Exception {
        DoctorsEntity doctor1 = new DoctorsEntity("Doctor A", "Surname A", "Speciality A", "imgA.jpg");
        DoctorsEntity doctor2 = new DoctorsEntity("Doctor B", "Surname B", "Speciality B", "imgB.jpg");
        doctor1.setId(1);
        doctor2.setId(2);
        List<DoctorsEntity> doctorsList = Arrays.asList(doctor1, doctor2);

        when(doctorsService.getAllDoctors()).thenReturn(doctorsList);

        mockMvc.perform(get("/doctors"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("Doctor A"))
                .andExpect(jsonPath("$[0].surname").value("Surname A"))
                .andExpect(jsonPath("$[0].speciality").value("Speciality A"))
                .andExpect(jsonPath("$[0].img").value("imgA.jpg"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].name").value("Doctor B"))
                .andExpect(jsonPath("$[1].surname").value("Surname B"))
                .andExpect(jsonPath("$[1].speciality").value("Speciality B"))
                .andExpect(jsonPath("$[1].img").value("imgB.jpg"));

        verify(doctorsService, times(1)).getAllDoctors();
    }

    @Test
    void getDoctorById() throws Exception {
        DoctorsEntity doctor = new DoctorsEntity("Doctor A", "Surname A", "Speciality A", "imgA.jpg");
        doctor.setId(1);

        when(doctorsService.getDoctorById(1)).thenReturn(doctor);

        mockMvc.perform(get("/doctors/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Doctor A"))
                .andExpect(jsonPath("$.surname").value("Surname A"))
                .andExpect(jsonPath("$.speciality").value("Speciality A"))
                .andExpect(jsonPath("$.img").value("imgA.jpg"));

        verify(doctorsService, times(1)).getDoctorById(1);
    }

    @Test
    void createDoctor() throws Exception {
        DoctorsEntity doctor = new DoctorsEntity("Doctor A", "Surname A", "Speciality A", "imgA.jpg");
        doctor.setId(1);

        when(doctorsService.saveDoctor(any(DoctorsEntity.class))).thenReturn(doctor);

        mockMvc.perform(post("/doctors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Doctor A\", \"surname\":\"Surname A\", \"speciality\":\"Speciality A\", \"img\":\"imgA.jpg\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Doctor A"))
                .andExpect(jsonPath("$.surname").value("Surname A"))
                .andExpect(jsonPath("$.speciality").value("Speciality A"))
                .andExpect(jsonPath("$.img").value("imgA.jpg"));

        verify(doctorsService, times(1)).saveDoctor(any(DoctorsEntity.class));
    }

    @Test
    void updateDoctor() throws Exception {
        DoctorsEntity doctor = new DoctorsEntity("Updated Doctor A", "Updated Surname A", "Updated Speciality A", "updatedImgA.jpg");
        doctor.setId(1);

        when(doctorsService.saveDoctor(any(DoctorsEntity.class))).thenReturn(doctor);

        mockMvc.perform(put("/doctors/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Updated Doctor A\", \"surname\":\"Updated Surname A\", \"speciality\":\"Updated Speciality A\", \"img\":\"updatedImgA.jpg\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Updated Doctor A"))
                .andExpect(jsonPath("$.surname").value("Updated Surname A"))
                .andExpect(jsonPath("$.speciality").value("Updated Speciality A"))
                .andExpect(jsonPath("$.img").value("updatedImgA.jpg"));

        verify(doctorsService, times(1)).saveDoctor(any(DoctorsEntity.class));
    }

    @Test
    void deleteDoctor() throws Exception {
        doNothing().when(doctorsService).deleteDoctor(1);

        mockMvc.perform(delete("/doctors/1"))
                .andExpect(status().isOk());

        verify(doctorsService, times(1)).deleteDoctor(1);
    }
}
