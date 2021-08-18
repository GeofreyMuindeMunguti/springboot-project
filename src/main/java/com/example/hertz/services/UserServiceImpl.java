package com.example.hertz.services;

import com.example.hertz.models.User;
import com.example.hertz.repositories.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements  UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Page<User> getAll(Integer page) {
        PageRequest pageable = PageRequest.of(0, 10);
        return userRepository.findAll(pageable);
    }

    @Override
    public User create(User user) {
        return userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

}
