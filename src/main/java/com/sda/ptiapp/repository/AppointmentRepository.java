package com.sda.ptiapp.repository;

import com.sda.ptiapp.model.Appointment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentRepository extends CrudRepository<Appointment, Integer> {

}
