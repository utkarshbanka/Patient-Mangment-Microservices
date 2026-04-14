package com.pm.patinetservice.controller;

import com.pm.patinetservice.dto.PatientRequestDTO;
import com.pm.patinetservice.dto.PatientResponseDTO;
import com.pm.patinetservice.service.PatientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.groups.Default;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/patient")
@RequiredArgsConstructor
@Tag(name = "Patient Controller",description = "This is The Patient Controller")
public class PatientController {

    private final PatientService patientService;


    @GetMapping
    @Operation(summary = "This is for getting all the Patients")
    public ResponseEntity<List<PatientResponseDTO>> getPatients() {
        return ResponseEntity.ok(patientService.getPatients());
    }


    @PostMapping
    @Operation(summary = "This is For The Creation of Patient")
    public ResponseEntity<PatientResponseDTO> createPatient(@Valid  @RequestBody PatientRequestDTO patientRequestDTO) {

        return ResponseEntity.ok(patientService.createPatient(patientRequestDTO));
    }

    @PutMapping("/{id}")
    @Operation(summary = "This is For Patient Update")
    public  ResponseEntity<PatientResponseDTO>  updatePatient(@PathVariable UUID id,
                                                              @Validated({Default.class}) @RequestBody PatientRequestDTO patientRequestDTO) {

          PatientResponseDTO patientResponseDTO = patientService.updatePatient(id,patientRequestDTO);
          return ResponseEntity.ok().body(patientResponseDTO);

    }
    @DeleteMapping("/{id}")
    @Operation(summary = "This is for Deleting The Patient")
    public ResponseEntity<Void> deletePatient(@PathVariable  UUID id) {
         patientService.deletePatient(id);
        return ResponseEntity.noContent().build();
    }


}
