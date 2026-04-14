package com.pm.analyticsservice.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import patient.event.PatientEvent;


@Service
public class KafkaConsumer {

    Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);

    @KafkaListener(topics = "patient" , groupId = "analytics-service")
    public void consumeEvent( byte [] events ){

        try{
            PatientEvent patientEvent = PatientEvent.parseFrom(events);
            System.out.println(patientEvent.toString());
            logger.info("Patient Event received"+patientEvent.toString());
        }catch (Exception e){
             logger.error(e.toString()+"Something went wrong");
        }
    }

}
