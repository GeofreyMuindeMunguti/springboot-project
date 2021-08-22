package com.example.hertz.services;

import com.example.hertz.RequestFilters.JwtTokenUtil;
import com.example.hertz.models.AuthToken;
import com.example.hertz.models.User;
import com.example.hertz.repositories.AuthTokenRepository;
import com.example.hertz.schemas.UserLoginRequest;
import com.example.hertz.schemas.UserLoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    public UserService userService;

    @Autowired
    public PasswordEncoder passwordEncoder;

    @Autowired
    public JwtTokenUtil jwtTokenUtil;


    public UserLoginResponse authenticate (UserLoginRequest request) {
            boolean authenticate = authenticate(request.getUsername(), request.getPassword());

            if (authenticate == true) {

                User user = userService.findByUsername(request.getUsername());

                UserLoginResponse userLoginResponse = new UserLoginResponse(
                        user.getUsername(),
                        jwtTokenUtil.generateToken(user),
                        user.getRoles()
                );

                return userLoginResponse;
            }else {
                return null;
            }
    }

    private boolean authenticate(String username, String password) {
        User user = userService.findByUsername(username);
        return passwordEncoder.matches(password, user.getPassword());
    }

}
