package com.example.hertz.services;

import com.example.hertz.models.User;

import java.util.List;
import java.util.UUID;

public interface UserService {
    List<User> getAll();

    User create(User user);

//    User loadByUserName(String username);
}
