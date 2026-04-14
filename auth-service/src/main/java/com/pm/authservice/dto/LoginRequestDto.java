package com.pm.authservice.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class LoginRequestDto {


    @NotBlank(message = "Email Is Required")
    @Email(message = "Email Should be Valid Email Address")
    private String email;

    @NotBlank(message = "Password Is Required")
    @Size(min = 8,message = "Password Should be More Then 8 Character")
    private String password;


}
