package com.example.hertz.services;

import com.example.hertz.models.User;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface UserService {
    Page<User> getAll(Integer page);

    User create(User user);

    User findByUsername(String username);

    User activate(User user);

    User instance();

    User getById(UUID id);
}
