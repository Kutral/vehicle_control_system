package com.example.vehicle.service;

import com.example.vehicle.model.Vehicle;
import org.springframework.stereotype.Service;

@Service
public class VehicleService {

    private final Vehicle vehicle = new Vehicle();

    public Vehicle getVehicleStatus() {
        return vehicle;
    }

    public void toggleEngine() {
        vehicle.setEngineOn(!vehicle.isEngineOn());
        if (!vehicle.isEngineOn()) {
            vehicle.setSpeed(0); // Engine off kills speed immediately in this sim
        }
    }

    public void accelerate(int amount) {
        if (!vehicle.isEngineOn()) {
            throw new IllegalStateException("Cannot accelerate: Engine is off.");
        }
        if (vehicle.isBrakeEngaged()) {
             throw new IllegalStateException("Cannot accelerate: Brake is engaged.");
        }
        if (vehicle.getFuelLevel() <= 0) {
            vehicle.setEngineOn(false);
            vehicle.setSpeed(0);
            throw new IllegalStateException("Cannot accelerate: Out of fuel.");
        }

        int potentialSpeed = vehicle.getSpeed() + amount;
        vehicle.setSpeed(Math.min(potentialSpeed, Vehicle.MAX_SPEED));

        // Simulate fuel consumption and distance (simplified)
        consumeFuelAndTravel();
    }

    public void refuel() {
        vehicle.setFuelLevel(Vehicle.MAX_FUEL);
    }

    private void consumeFuelAndTravel() {
        // Simple simulation: Higher speed = more fuel consumed
        // distance = speed * time (assuming 1 unit of time per action for simplicity)
        double speed = vehicle.getSpeed();
        if (speed > 0) {
            double consumption = (speed * 0.05); // arbitrary unit
            double currentFuel = vehicle.getFuelLevel();
            double newFuel = Math.max(0, currentFuel - consumption);
            vehicle.setFuelLevel(newFuel);

            if (newFuel == 0) {
                vehicle.setEngineOn(false);
                vehicle.setSpeed(0);
            }

            // Update odometer (assuming 1 action = 1 minute for simulation sake, speed in km/h)
            // distance = speed (km/h) * (1/60) h
            double distance = speed / 60.0;
            vehicle.setOdometer(vehicle.getOdometer() + distance);
            vehicle.setTripDistance(vehicle.getTripDistance() + distance);
        }
    }

    public void brake() {
        vehicle.setBrakeEngaged(true);
        vehicle.setSpeed(0);
    }

    public void releaseBrake() {
        vehicle.setBrakeEngaged(false);
    }

    public void steer(int angle) {
        // Clamp angle between -MAX and MAX
        int clampedAngle = Math.max(-Vehicle.MAX_STEERING_ANGLE, Math.min(angle, Vehicle.MAX_STEERING_ANGLE));
        vehicle.setSteeringAngle(clampedAngle);
    }

    public void setGear(int gear) {
        if (gear < -1 || gear > 5) {
             throw new IllegalArgumentException("Invalid gear selection.");
        }
        vehicle.setGear(gear);
    }

    public void reset() {
        vehicle.setSpeed(0);
        vehicle.setSteeringAngle(0);
        vehicle.setEngineOn(false);
        vehicle.setBrakeEngaged(false);
        vehicle.setGear(1);
    }
}
