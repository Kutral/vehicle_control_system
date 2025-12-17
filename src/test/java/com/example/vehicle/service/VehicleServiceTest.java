package com.example.vehicle.service;

import com.example.vehicle.model.Vehicle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class VehicleServiceTest {

    private VehicleService vehicleService;

    @BeforeEach
    void setUp() {
        vehicleService = new VehicleService();
    }

    @Test
    void testInitialState() {
        Vehicle v = vehicleService.getVehicleStatus();
        assertFalse(v.isEngineOn());
        assertEquals(0, v.getSpeed());
    }

    @Test
    void testEngineStart() {
        vehicleService.toggleEngine();
        assertTrue(vehicleService.getVehicleStatus().isEngineOn());
    }

    @Test
    void testAccelerateWithoutEngine() {
        assertThrows(IllegalStateException.class, () -> vehicleService.accelerate(10));
    }

    @Test
    void testAccelerateWithEngine() {
        vehicleService.toggleEngine();
        vehicleService.accelerate(10);
        assertEquals(10, vehicleService.getVehicleStatus().getSpeed());
    }

    @Test
    void testBraking() {
        vehicleService.toggleEngine();
        vehicleService.accelerate(50);
        vehicleService.brake();
        assertEquals(0, vehicleService.getVehicleStatus().getSpeed());
        assertTrue(vehicleService.getVehicleStatus().isBrakeEngaged());
    }

    @Test
    void testSteeringClamp() {
        vehicleService.steer(100);
        assertEquals(45, vehicleService.getVehicleStatus().getSteeringAngle());

        vehicleService.steer(-100);
        assertEquals(-45, vehicleService.getVehicleStatus().getSteeringAngle());
    }
}
