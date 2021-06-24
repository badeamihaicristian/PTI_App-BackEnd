package com.sda.ptiapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class ExceptionsHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({VehicleNotFoundException.class, ClientNotFoundException.class, AppointmentNotFoundException.class})
    public ResponseEntity<Object> handleModelsNotFoundException(RuntimeException exception) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timeStamp", LocalDateTime.now());
        body.put("message", exception.getLocalizedMessage());

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }
}

