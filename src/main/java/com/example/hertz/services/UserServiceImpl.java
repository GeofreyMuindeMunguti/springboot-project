package com.example.hertz.services;

import com.example.hertz.models.User;
import com.example.hertz.repositories.UserRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserServiceImpl implements  UserService{

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User create(User user) {
        return userRepository.save(user);
    }

}
