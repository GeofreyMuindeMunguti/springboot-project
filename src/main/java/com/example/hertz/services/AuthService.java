package com.example.hertz.services;

import com.example.hertz.models.AuthToken;
import com.example.hertz.models.User;
import com.example.hertz.schemas.UserLoginRequest;
import com.example.hertz.schemas.UserLoginResponse;

import java.util.List;

public interface AuthService {
     UserLoginResponse authenticate(UserLoginRequest userLoginRequest);

     AuthToken saveToken(AuthToken authToken);

     List<AuthToken> getByUser(User user);

}
