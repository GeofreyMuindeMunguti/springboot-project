package com.example.hertz.services;

import com.example.hertz.RequestFilters.JwtTokenUtil;
import com.example.hertz.models.AuthToken;
import com.example.hertz.models.User;
import com.example.hertz.repositories.AuthTokenRepository;
import com.example.hertz.schemas.UserLoginRequest;
import com.example.hertz.schemas.UserLoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    public UserService userService;

    @Autowired
    public PasswordEncoder passwordEncoder;

    @Autowired
    public JwtTokenUtil jwtTokenUtil;

    @Autowired
    public AuthTokenRepository authTokenRepository;

    @Override
    public UserLoginResponse authenticate (UserLoginRequest request) {
            boolean authenticate = authenticate(request.getUsername(), request.getPassword());


            if (authenticate == true) {

                User user = userService.findByUsername(request.getUsername());

                List<AuthToken> authTokens = getByUser(user);
                String token = "";

                if (authTokens.isEmpty()) {
                    token = jwtTokenUtil.generateToken(user);

                    Date expiryDate = new Date();

                    AuthToken authToken = new AuthToken(token, expiryDate, user);
                    AuthToken saved = saveToken(authToken);
                }
                else {
                    token = authTokens.get(0).getToken();
                }

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

    @Override
    public AuthToken saveToken(AuthToken token){
        return authTokenRepository.save(token);
    }

    @Override
    public List<AuthToken> getByUser(User user){
        return authTokenRepository.findByUser(user);
    }

}
