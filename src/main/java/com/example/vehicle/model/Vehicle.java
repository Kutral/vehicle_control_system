package com.example.vehicle.model;

import lombok.Data;

@Data
public class Vehicle {
    private int speed = 0;
    private int steeringAngle = 0;
    private boolean engineOn = false;
    private boolean brakeEngaged = false;
    private int gear = 1; // 1-5, 0 for neutral, -1 for reverse

    // Constraints
    public static final int MAX_SPEED = 200;
    public static final int MAX_STEERING_ANGLE = 45; // Degrees left/right
}
