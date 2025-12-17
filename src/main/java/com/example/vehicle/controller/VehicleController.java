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

@Controller
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
    public Vehicle getVehicleApi() {
        return vehicleService.getVehicleStatus();
    }

    @PostMapping("/api/control")
    @ResponseBody
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
