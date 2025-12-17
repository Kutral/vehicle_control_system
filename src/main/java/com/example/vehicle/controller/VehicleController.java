package com.example.vehicle.controller;

import com.example.vehicle.model.Vehicle;
import com.example.vehicle.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Controller
@Tag(name = "Vehicle Control", description = "Endpoints for controlling the vehicle")
public class VehicleController {

    private final VehicleService vehicleService;

    @Autowired
    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("vehicle", vehicleService.getVehicleStatus());
        return "dashboard";
    }

    // API Endpoint for programmatic access (The "Surprise")
    @GetMapping("/api/vehicle")
    @ResponseBody
    @Operation(summary = "Get Vehicle Status", description = "Returns the current state of the vehicle including speed, fuel, etc.")
    public Vehicle getVehicleApi() {
        return vehicleService.getVehicleStatus();
    }

    @PostMapping("/api/vehicle/refuel")
    @ResponseBody
    @Operation(summary = "Refuel", description = "Refuels the vehicle to 100%")
    public Vehicle refuel() {
        vehicleService.refuel();
        return vehicleService.getVehicleStatus();
    }

    @PostMapping("/api/control")
    @ResponseBody
    @Operation(summary = "Control Vehicle", description = "Send various commands to the vehicle (accelerate, brake, steer, etc.)")
    public Vehicle controlVehicle(
            @RequestParam(required = false) String action,
            @RequestParam(required = false) Integer value) {

        if (action != null) {
            try {
                switch (action.toLowerCase()) {
                    case "accelerate":
                        if (value != null) vehicleService.accelerate(value);
                        break;
                    case "brake":
                        vehicleService.brake();
                        break;
                    case "release_brake":
                        vehicleService.releaseBrake();
                        break;
                    case "steer":
                        if (value != null) vehicleService.steer(value);
                        break;
                    case "toggle_engine":
                        vehicleService.toggleEngine();
                        break;
                     case "gear":
                        if (value != null) vehicleService.setGear(value);
                        break;
                     case "refuel":
                        vehicleService.refuel();
                        break;
                    case "reset":
                        vehicleService.reset();
                        break;
                }
            } catch (Exception e) {
                // In a real app, handle error properly. For now, just ignore invalid actions.
            }
        }
        return vehicleService.getVehicleStatus();
    }

    // Form Handlers for the Dashboard
    @PostMapping("/control")
    public String control(
            @RequestParam String action,
            @RequestParam(required = false) Integer value,
            Model model) {

        controlVehicle(action, value);
        return "redirect:/";
    }
}
