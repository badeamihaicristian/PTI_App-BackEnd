package com.sda.ptiapp.controller;

import com.sda.ptiapp.model.Vehicle;
import com.sda.ptiapp.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vehicles")
@CrossOrigin
public class VehicleController {

    private final VehicleService service;

    @Autowired
    public VehicleController(VehicleService service) {
        this.service = service;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Vehicle>> getAllVehicles() {
        return ResponseEntity.ok(service.getAllVehicles());
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<Vehicle> getVehicleById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(service.findVehicleById(id));
    }

    @PostMapping("/add")
    public void addVehicle(@RequestBody Vehicle vehicle) {
        service.addVehicle(vehicle);
    }

    @PutMapping("/update/{id}")
    public void updateVehicle(@PathVariable("id") Integer id, @RequestBody Vehicle vehicle) {
        service.updateVehicleById(id, vehicle);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteVehicle(@PathVariable("id") Integer id) {
        service.deleteVehicleById(id);
    }
}
