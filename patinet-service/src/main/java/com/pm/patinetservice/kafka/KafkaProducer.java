package com.pm.patinetservice.kafka;

import com.pm.patinetservice.model.Patient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import patient.event.PatientEvent;

//@RequiredArgsConstructor
@Service
@Slf4j
public class KafkaProducer {

    private final KafkaTemplate<Object, byte[]> kafkaTemplate;


    public KafkaProducer(KafkaTemplate<Object, byte[]> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

     public void sendEvent(Patient patient){
         PatientEvent event = PatientEvent.newBuilder().
                 setPatientId(patient.getId().toString())
                 .setEmail(patient.getEmail())
                 .setName(patient.getName())
                 .setEventType("PATIENT_CREATED").build();
         try{
             kafkaTemplate.send("patient", event.toByteArray());
         }catch (Exception e){
             log.info("Kafka producer send event failed", event);
         }
     }

}
