package com.sda.ptiapp.service;

import com.sda.ptiapp.exception.VehicleNotFoundException;
import com.sda.ptiapp.model.Vehicle;
import com.sda.ptiapp.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class VehicleService {

    VehicleRepository vehicleRepository;

    @Autowired
    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    public void addVehicle(Vehicle vehicle) {
        vehicleRepository.save(vehicle);
    }

    public List<Vehicle> getAllVehicles() {
        return (List<Vehicle>) vehicleRepository.findAll();
    }

    public Vehicle findVehicleById(Integer id) {
        return vehicleRepository.findById(id)
                .orElseThrow(() -> new VehicleNotFoundException("Vehicle with id " + id + " not found"));
    }

    public void deleteVehicleById(Integer id) {
        Optional<Vehicle> vehicle = vehicleRepository.findById(id);
        if (vehicle.isPresent()) {
            vehicleRepository.deleteById(vehicle.get().getId());
        } else {
            throw new VehicleNotFoundException("Vehicle with id " + id + " not found");
        }
    }

    public void updateVehicleById(Integer id, Vehicle newVehicle) {
    Vehicle oldVehicle = findVehicleById(id);
    updateVehicle(newVehicle, oldVehicle);
    vehicleRepository.save(oldVehicle);
    }

    private void updateVehicle(Vehicle newVehicle, Vehicle oldVehicle) {
        oldVehicle.setMade(newVehicle.getMade());
        oldVehicle.setOwner(newVehicle.getOwner());
        oldVehicle.setColor((newVehicle.getColor()));
        oldVehicle.setCategory(newVehicle.getCategory());
        oldVehicle.setFuelType(newVehicle.getFuelType());
        oldVehicle.setModel(newVehicle.getModel());
        oldVehicle.setLicenceNo(newVehicle.getLicenceNo());
        oldVehicle.setYear(newVehicle.getYear());
        oldVehicle.setChassisNo(newVehicle.getChassisNo());
        oldVehicle.setDrivetrain(newVehicle.getDrivetrain());
    }
}
