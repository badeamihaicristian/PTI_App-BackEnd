package com.sda.ptiapp.repository;

import com.sda.ptiapp.model.Vehicle;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepository extends CrudRepository<Vehicle, Integer> {

}
