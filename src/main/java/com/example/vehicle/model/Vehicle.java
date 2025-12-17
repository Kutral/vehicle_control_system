package com.example.vehicle.model;

import lombok.Data;

@Data
public class Vehicle {
    private int speed = 0;
    private int steeringAngle = 0;
    private boolean engineOn = false;
    private boolean brakeEngaged = false;
    private int gear = 1; // 1-5, 0 for neutral, -1 for reverse

    // New Features
    private double fuelLevel = 100.0; // Percentage
    private double odometer = 0.0; // km
    private double tripDistance = 0.0; // km

    // Constraints
    public static final int MAX_SPEED = 200;
    public static final int MAX_STEERING_ANGLE = 45; // Degrees left/right
    public static final double MAX_FUEL = 100.0;
}
