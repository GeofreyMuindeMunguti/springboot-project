package com.example.hertz.services;

import com.example.hertz.models.User;
import org.springframework.data.domain.Page;

import java.awt.print.Pageable;

public interface UserService {
    Page<User> getAll(Integer page);

    User create(User user);

    User findByUsername(String username);

}
