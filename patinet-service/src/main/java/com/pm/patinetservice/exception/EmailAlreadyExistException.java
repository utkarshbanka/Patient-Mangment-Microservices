package com.pm.patinetservice.exception;

import jakarta.validation.constraints.Email;

public class EmailAlreadyExistException  extends  RuntimeException{


   public  EmailAlreadyExistException(String message){
        super(message);
    }

    public EmailAlreadyExistException(){

    }
}
