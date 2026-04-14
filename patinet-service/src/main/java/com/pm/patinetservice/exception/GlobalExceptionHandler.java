package com.pm.patinetservice.exception;

import com.pm.patinetservice.model.Patient;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<Map<String,String>> registerPatient(MethodArgumentNotValidException exception) {


        Map<String,String> map = new HashMap<>();


        exception.getBindingResult().getFieldErrors().forEach((fieldError) -> {
            map.put(fieldError.getField(), fieldError.getDefaultMessage());
        });

//        return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
          return ResponseEntity.badRequest().body(map);
    }


    @ExceptionHandler({EmailAlreadyExistException.class})
    public ResponseEntity<Map<String,String>> emailAlreadyExist(EmailAlreadyExistException exception){
        Map<String,String> map = new HashMap<>();

        map.put(exception.getMessage(), exception.getMessage());
        return ResponseEntity.badRequest().body(map);
    }

    @ExceptionHandler({PatientNotFoundException.class})
    public ResponseEntity<Map<String,String>> registerPatient(PatientNotFoundException exception){
        Map<String,String> map = new HashMap<>();
        map.put(exception.getMessage(), exception.getMessage());
        return ResponseEntity.badRequest().body(map);
    }

}
