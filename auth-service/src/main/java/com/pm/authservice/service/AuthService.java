package com.pm.authservice.service;

import com.pm.authservice.dto.LoginRequestDto;
import com.pm.authservice.dto.LoginResponseDto;
import com.pm.authservice.model.User;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Service
public class AuthService {


     private  UserService userService;
     public AuthService(UserService userService) {
        this.userService = userService;
     }

      public Optional<LoginResponseDto> authenticate(LoginRequestDto loginRequestDto) {

          Optional<User>  user = userService.findByEmail(loginRequestDto.getEmail());


      }
}
