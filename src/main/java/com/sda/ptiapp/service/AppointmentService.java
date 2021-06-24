package com.sda.ptiapp.service;

import com.sda.ptiapp.exception.AppointmentNotFoundException;
import com.sda.ptiapp.model.Appointment;
import com.sda.ptiapp.model.other.Report;
import com.sda.ptiapp.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AppointmentService {

    AppointmentRepository appointmentRepository;

    @Autowired
    public AppointmentService(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    public void addAppointment(Appointment appointment) {
        appointmentRepository.save(appointment);
    }

    public List<Appointment> getAllFutureAppointments() {
        return ((List<Appointment>) appointmentRepository.findAll())
                .stream().filter(appointment -> !appointment.isFinalised())
                .collect(Collectors.toList());
    }

    public List<Appointment> getAllPastAppointments() {
        return ((List<Appointment>) appointmentRepository.findAll())
                .stream().filter(Appointment::isFinalised)
                .collect(Collectors.toList());
    }

    public List<Appointment> getAllSoonToExpireAppointments() {
        List<Appointment> soonToExpireAppointments = new ArrayList<>();
        for (Appointment appointment : getAllPastAppointments()) {
            LocalDate dateOfExpire = LocalDate.parse(appointment.getExpirationDate());
            if (ChronoUnit.DAYS.between(LocalDate.now(), dateOfExpire) < 10 && LocalDate.now().isBefore(dateOfExpire)) {
                soonToExpireAppointments.add(appointment);
            }
        }
        return soonToExpireAppointments;
    }

    public Appointment findAppointmentById(Integer id) {
        return appointmentRepository.findById(id)
                .orElseThrow(() -> new AppointmentNotFoundException("Appointment with id " + id + " not found"));
    }

    public void updateAppointmentById(Integer id, Appointment newAppointment) {
        Appointment oldAppointment = findAppointmentById(id);
        updateAppointment(newAppointment, oldAppointment);
        appointmentRepository.save(oldAppointment);
    }

    public void finalizeAppointment(Integer id, Appointment newAppointment) {
        Appointment oldAppointment = findAppointmentById(id);
        oldAppointment.setFinalised(true);
        updateAppointment(newAppointment, oldAppointment);
        appointmentRepository.save(oldAppointment);
    }

    public void deleteAppointmentById(Integer id) {
        Optional<Appointment> appointment = appointmentRepository.findById(id);
        if (appointment.isPresent()) {
            appointmentRepository.deleteById(appointment.get().getId());
        } else {
            throw new AppointmentNotFoundException("Appointment with id " + id + " not found");
        }
    }

    public Report getReport(String startDate, String endDate) {
        List<Appointment> listOfAppointmentsForReport = new ArrayList<>();
        Report report = new Report();
        LocalDate startDateAsDate = LocalDate.parse(startDate);
        LocalDate endDateAsDate = LocalDate.parse(endDate).plusDays(2);
        for (Appointment appointment : getAllPastAppointments()) {
            if ((LocalDate.parse(appointment.getAppointmentDate())).isAfter(startDateAsDate)
                    && (LocalDate.parse(appointment.getAppointmentDate())).isBefore(endDateAsDate)) {
                listOfAppointmentsForReport.add(appointment);
            }
        }
        report.setNoOfAppointments(listOfAppointmentsForReport.size());
        report.setNoOfCars((int) listOfAppointmentsForReport.stream()
                .filter(appointment -> appointment.getVehicle().getCategory().equals("Autoturism")).count());
        report.setNoOfMotorcycles((int) listOfAppointmentsForReport.stream()
                .filter(appointment -> appointment.getVehicle().getCategory().equals("Motocicleta")).count());
        report.setNoOfUtilityVehicles((int) listOfAppointmentsForReport.stream()
                .filter(appointment -> appointment.getVehicle().getCategory().equals("Autoutilitara")).count());
        report.setNoOfTrucks((int) listOfAppointmentsForReport.stream()
                .filter(appointment -> appointment.getVehicle().getCategory().equals("Camion")).count());
        report.setNoOfTrailers((int) listOfAppointmentsForReport.stream()
                .filter(appointment -> appointment.getVehicle().getCategory().equals("Remorca")).count());
        report.setNoOfGas((int) listOfAppointmentsForReport.stream()
                .filter(appointment -> appointment.getVehicle().getFuelType().equals("Benzina")).count());
        report.setNoOfDiesel((int) listOfAppointmentsForReport.stream()
                .filter(appointment -> appointment.getVehicle().getFuelType().equals("Motorina")).count());
        report.setNoOfElectric((int) listOfAppointmentsForReport.stream()
                .filter(appointment -> appointment.getVehicle().getFuelType().equals("Electric")).count());
        report.setNoOfHybrid((int) listOfAppointmentsForReport.stream()
                .filter(appointment -> appointment.getVehicle().getFuelType().equals("Hibrid")).count());
        return report;
    }

    private void updateAppointment(Appointment newAppointment, Appointment oldAppointment) {
        oldAppointment.setAppointmentDate(newAppointment.getAppointmentDate());
        oldAppointment.setExpirationDate(newAppointment.getExpirationDate());
        oldAppointment.setClient(newAppointment.getClient());
        oldAppointment.setHour(newAppointment.getHour());
        oldAppointment.setComments(newAppointment.getComments());
        oldAppointment.setVehicle(newAppointment.getVehicle());
        oldAppointment.setResults(newAppointment.getResults());
    }
}
