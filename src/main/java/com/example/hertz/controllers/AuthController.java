package com.example.hertz.controllers;


import com.example.hertz.schemas.UserLoginRequest;
import com.example.hertz.schemas.UserLoginResponse;
import com.example.hertz.services.AuthServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "auth")
public class AuthController {

    @Autowired
    private AuthServiceImpl authService;

    @Autowired
    private AuthenticationManager authenticationManager;


    @PostMapping("login")
    public ResponseEntity<UserLoginResponse> login(@RequestBody UserLoginRequest request) {
        return ResponseEntity.ok().body(authService.authenticate(request));
    }


}
