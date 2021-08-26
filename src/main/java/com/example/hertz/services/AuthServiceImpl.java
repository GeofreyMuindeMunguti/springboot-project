package com.example.hertz.services;

import com.example.hertz.RequestFilters.JwtTokenUtil;
import com.example.hertz.models.AuthToken;
import com.example.hertz.models.User;
import com.example.hertz.repositories.AuthTokenRepository;
import com.example.hertz.schemas.UserLoginRequest;
import com.example.hertz.schemas.UserLoginResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class AuthServiceImpl implements AuthService {

    public final UserService userService;

    public final PasswordEncoder passwordEncoder;

    public final JwtTokenUtil jwtTokenUtil;

    public final AuthTokenRepository authTokenRepository;

    public AuthServiceImpl(UserService userService, PasswordEncoder passwordEncoder, JwtTokenUtil jwtTokenUtil, AuthTokenRepository authTokenRepository) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenUtil = jwtTokenUtil;
        this.authTokenRepository = authTokenRepository;
    }

    @Override
    public UserLoginResponse authenticate (UserLoginRequest request) {
            boolean authenticate = authenticate(request.getUsername(), request.getPassword());

            if (authenticate == true) {

                User user = userService.findByUsername(request.getUsername());

                if(!user.isEnabled)
                    throw new ResponseStatusException(
                            HttpStatus.NOT_FOUND, "User not Active!");

                List<AuthToken> authTokens = getByUser(user);
                Integer tokens_length = authTokens.size() - 1;
                String token = "";

                Date today = new Date();

                if (authTokens.isEmpty()) {
                    createAndSaveNewToken(user);
                }
                else if (!authTokens.isEmpty() &&
                        today.after(authTokens.get(tokens_length).getExpiryDate()) ){
                    createAndSaveNewToken(user);
                }
                else {
                    token = authTokens.get(tokens_length).getToken();
                }

                UserLoginResponse userLoginResponse = new UserLoginResponse(
                        user.getUsername(),
                        jwtTokenUtil.generateToken(user),
                        user.getRoles()
                );

                return userLoginResponse;
            }else {
                throw new ResponseStatusException(
                        HttpStatus.UNAUTHORIZED, "Login failed");
            }
    }

    private void createAndSaveNewToken(User user) {
        String token;
        token = jwtTokenUtil.generateToken(user);

        Date expiryDate = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(expiryDate);
        c.add(Calendar.DATE, 30); // Token expires after this number of days.
        expiryDate = c.getTime();

        AuthToken authToken = new AuthToken(token, expiryDate, user);
        AuthToken saved = saveToken(authToken);
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
