package com.pm.patinetservice.mapper;

import com.pm.patinetservice.dto.PatientRequestDTO;
import com.pm.patinetservice.dto.PatientResponseDTO;
import com.pm.patinetservice.model.Patient;

import java.time.LocalDate;

public class PatientMapper {

    public  static  PatientResponseDTO todto(Patient p) {
        PatientResponseDTO patientResponseDTO = new PatientResponseDTO();
        patientResponseDTO.setId(p.getId().toString());
        patientResponseDTO.setName(p.getName());
        patientResponseDTO.setAddress(p.getAddress());
        patientResponseDTO.setEmail(p.getEmail());
        patientResponseDTO.setDateOfBirth(p.getDateOfBirth().toString());
        return patientResponseDTO;
    }

     public  static Patient  toModel(PatientRequestDTO patientRequestDTO) {
        Patient patient = new Patient();
        patient.setName(patientRequestDTO.getName());
        patient.setAddress(patientRequestDTO.getAddress());
        patient.setEmail(patientRequestDTO.getEmail());
        patient.setDateOfBirth(LocalDate.parse(patientRequestDTO.getDateOfBirth()));
        patient.setRegisteredDate(LocalDate.parse(patientRequestDTO.getRegisteredDate()));
        return patient;
     }
}
