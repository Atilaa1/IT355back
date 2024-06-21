package org.example.it355pz;

import org.example.it355pz.controller.MedicineController;
import org.example.it355pz.model.MedicineEntity;
import org.example.it355pz.service.MedicineService;
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

class MedicineControllerTest {

    private MockMvc mockMvc;

    @Mock
    private MedicineService medicineService;

    @InjectMocks
    private MedicineController medicineController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(medicineController).build();
    }

    @Test
    void getAllMedicines() throws Exception {
        MedicineEntity medicine1 = new MedicineEntity("Medicine A", "Company A", "Dose A", "Description A", "imgA.jpg");
        MedicineEntity medicine2 = new MedicineEntity("Medicine B", "Company B", "Dose B", "Description B", "imgB.jpg");
        medicine1.setId(1);
        medicine2.setId(2);
        List<MedicineEntity> medicinesList = Arrays.asList(medicine1, medicine2);

        when(medicineService.getAllMedicines()).thenReturn(medicinesList);

        mockMvc.perform(get("/api/medicines"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("Medicine A"))
                .andExpect(jsonPath("$[0].company").value("Company A"))
                .andExpect(jsonPath("$[0].dose").value("Dose A"))
                .andExpect(jsonPath("$[0].description").value("Description A"))
                .andExpect(jsonPath("$[0].img").value("imgA.jpg"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].name").value("Medicine B"))
                .andExpect(jsonPath("$[1].company").value("Company B"))
                .andExpect(jsonPath("$[1].dose").value("Dose B"))
                .andExpect(jsonPath("$[1].description").value("Description B"))
                .andExpect(jsonPath("$[1].img").value("imgB.jpg"));

        verify(medicineService, times(1)).getAllMedicines();
    }

    @Test
    void getMedicineById() throws Exception {
        MedicineEntity medicine = new MedicineEntity("Medicine A", "Company A", "Dose A", "Description A", "imgA.jpg");
        medicine.setId(1);

        when(medicineService.getMedicineById(1)).thenReturn(medicine);

        mockMvc.perform(get("/api/medicines/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Medicine A"))
                .andExpect(jsonPath("$.company").value("Company A"))
                .andExpect(jsonPath("$.dose").value("Dose A"))
                .andExpect(jsonPath("$.description").value("Description A"))
                .andExpect(jsonPath("$.img").value("imgA.jpg"));

        verify(medicineService, times(1)).getMedicineById(1);
    }

    @Test
    void createMedicine() throws Exception {
        MedicineEntity medicine = new MedicineEntity("Medicine A", "Company A", "Dose A", "Description A", "imgA.jpg");
        medicine.setId(1);

        when(medicineService.saveMedicine(any(MedicineEntity.class))).thenReturn(medicine);

        mockMvc.perform(post("/api/medicines")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Medicine A\", \"company\":\"Company A\", \"dose\":\"Dose A\", \"description\":\"Description A\", \"img\":\"imgA.jpg\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Medicine A"))
                .andExpect(jsonPath("$.company").value("Company A"))
                .andExpect(jsonPath("$.dose").value("Dose A"))
                .andExpect(jsonPath("$.description").value("Description A"))
                .andExpect(jsonPath("$.img").value("imgA.jpg"));

        verify(medicineService, times(1)).saveMedicine(any(MedicineEntity.class));
    }

    @Test
    void updateMedicine() throws Exception {
        MedicineEntity medicine = new MedicineEntity("Updated Medicine A", "Updated Company A", "Updated Dose A", "Updated Description A", "updatedImgA.jpg");
        medicine.setId(1);

        when(medicineService.saveMedicine(any(MedicineEntity.class))).thenReturn(medicine);

        mockMvc.perform(put("/api/medicines/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Updated Medicine A\", \"company\":\"Updated Company A\", \"dose\":\"Updated Dose A\", \"description\":\"Updated Description A\", \"img\":\"updatedImgA.jpg\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Updated Medicine A"))
                .andExpect(jsonPath("$.company").value("Updated Company A"))
                .andExpect(jsonPath("$.dose").value("Updated Dose A"))
                .andExpect(jsonPath("$.description").value("Updated Description A"))
                .andExpect(jsonPath("$.img").value("updatedImgA.jpg"));

        verify(medicineService, times(1)).saveMedicine(any(MedicineEntity.class));
    }

    @Test
    void deleteMedicine() throws Exception {
        doNothing().when(medicineService).deleteMedicine(1);

        mockMvc.perform(delete("/api/medicines/1"))
                .andExpect(status().isOk());

        verify(medicineService, times(1)).deleteMedicine(1);
    }
}