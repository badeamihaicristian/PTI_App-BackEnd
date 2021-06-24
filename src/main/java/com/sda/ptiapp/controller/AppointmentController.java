package com.sda.ptiapp.controller;

import com.sda.ptiapp.model.Appointment;
import com.sda.ptiapp.model.other.Report;
import com.sda.ptiapp.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appointments")
@CrossOrigin
public class AppointmentController {

    private final AppointmentService appointmentService;

    @Autowired
    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @GetMapping("/all")
    //randul de mai sus e echivalent cu --->    @RequestMapping(method = RequestMethod.GET, path = "/all")
    public ResponseEntity<List<Appointment>> getAllFutureAppointments() {
        return ResponseEntity.ok(appointmentService.getAllFutureAppointments());
    }

    @GetMapping("/allPast")
    public ResponseEntity<List<Appointment>> getAllPastAppointments() {
        return ResponseEntity.ok(appointmentService.getAllPastAppointments());
    }

    @GetMapping("/report/{dates}")
    public ResponseEntity<Report> getReport(@PathVariable String dates) {
        String startDate = dates.substring(0, 10);
        String endDate = dates.substring(10, 20);
        return ResponseEntity.ok(appointmentService.getReport(startDate, endDate));
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<Appointment> getAppointmentById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(appointmentService.findAppointmentById(id));
    }

    @GetMapping("/allSoonToExpire")
    public ResponseEntity<List<Appointment>> getAllSoonToExpireAppointments() {
        return ResponseEntity.ok(appointmentService.getAllSoonToExpireAppointments());
    }

    @PostMapping("/add")
    public void addAppointment(@RequestBody Appointment appointment) {
        appointmentService.addAppointment(appointment);
    }

    @PutMapping("/update/{id}")
    public void updateAppointment(@PathVariable("id") Integer id, @RequestBody Appointment appointment) {
        appointmentService.updateAppointmentById(id, appointment);
    }

    @PutMapping("/finalize/{id}")
    public void finalizeAppointment(@PathVariable("id") Integer id, @RequestBody Appointment appointment) {
        appointmentService.finalizeAppointment(id, appointment);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteAppointment(@PathVariable("id") Integer id) {
        appointmentService.deleteAppointmentById(id);
    }
}
