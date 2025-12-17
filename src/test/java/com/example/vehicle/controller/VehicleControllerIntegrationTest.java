package com.example.vehicle.controller;

import com.example.vehicle.service.VehicleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class VehicleControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private VehicleService vehicleService;

    @Test
    void testDashboardLoads() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("dashboard"))
                .andExpect(model().attributeExists("vehicle"));
    }

    @Test
    void testApiControl() throws Exception {
        mockMvc.perform(post("/api/control")
                        .param("action", "toggle_engine"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.engineOn").value(true));

        mockMvc.perform(post("/api/control")
                        .param("action", "accelerate")
                        .param("value", "20"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.speed").value(20));
    }
}
