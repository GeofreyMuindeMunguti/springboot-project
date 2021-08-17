package com.example.hertz.controllers;


import com.example.hertz.RequestFilters.JwtTokenUtil;
import com.example.hertz.models.User;
import com.example.hertz.services.UserService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public AuthController(AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil, UserService userService, AuthenticationManager authManager, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }


    @PostMapping("login")
    public ResponseEntity login(@RequestBody User request) {
        try {
            boolean authenticate = authenticate(request.getUsername(), request.getPassword());

            if (authenticate == true) {
                User user = userService.findByUsername(request.getUsername());
                return ResponseEntity.ok()
                        .header(
                                HttpHeaders.AUTHORIZATION,
                                jwtTokenUtil.generateToken(user)
                        )
                        .body(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(403).body(null);
    }

    private boolean authenticate(String username, String password) throws Exception {
            User user = userService.findByUsername(username);
            return passwordEncoder.matches(password, user.getPassword());
    }

}
