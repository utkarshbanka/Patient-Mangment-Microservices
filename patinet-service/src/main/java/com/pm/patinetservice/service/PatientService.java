package com.pm.patinetservice.service;

import com.pm.patinetservice.dto.PatientRequestDTO;
import com.pm.patinetservice.dto.PatientResponseDTO;
import com.pm.patinetservice.exception.EmailAlreadyExistException;
import com.pm.patinetservice.exception.PatientNotFoundException;
import com.pm.patinetservice.grpc.BillingServiceGrpcClinet;
import com.pm.patinetservice.kafka.KafkaProducer;
import com.pm.patinetservice.mapper.PatientMapper;
import com.pm.patinetservice.model.Patient;
import com.pm.patinetservice.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.lang.reflect.ParameterizedType;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.pm.patinetservice.mapper.PatientMapper.toModel;
import static com.pm.patinetservice.mapper.PatientMapper.todto;

@Service
@RequiredArgsConstructor
public class PatientService {

    private final PatientRepository patientRepository;
    private final BillingServiceGrpcClinet billingServiceGrpcClinet;
    private final KafkaProducer kafkaProducer;


    public List<PatientResponseDTO> getPatients() {
        List<Patient> patients = patientRepository.findAll();
        return patients.
                stream().
                map(PatientMapper::todto).
                toList();

    }


    public  PatientResponseDTO createPatient(PatientRequestDTO patientRequestDTO) {

        if(patientRepository.existsByEmail((patientRequestDTO.getEmail()))) {
            throw new EmailAlreadyExistException("Email Already Exit");
        }
        Patient patient = patientRepository.save(toModel(patientRequestDTO));

        billingServiceGrpcClinet.
                creteBillingAccount(patient.getId().toString(),patient.getName(), patient.getEmail());


        kafkaProducer.sendEvent(patient);

        return todto(patient);
    }

    public  PatientResponseDTO updatePatient(UUID id , PatientRequestDTO patientRequestDTO) {

        Patient patient = patientRepository.findById(id).orElseThrow(()->
                new PatientNotFoundException("Patient Not Found for This Id"));


        if(patientRepository.existsByEmailAndIdNot(patientRequestDTO.getEmail(), id)) {
            throw new EmailAlreadyExistException("Email Already Exit");
        }

        patient.setName(patientRequestDTO.getName());
        patient.setEmail(patientRequestDTO.getEmail());
        patient.setAddress(patientRequestDTO.getAddress());
        patient.setDateOfBirth(LocalDate.parse(patientRequestDTO.getDateOfBirth()));

       Patient update =  patientRepository.save(patient);

       return todto(update);
    }

    public void deletePatient(UUID id) {
        patientRepository.deleteById(id);
    }

}
