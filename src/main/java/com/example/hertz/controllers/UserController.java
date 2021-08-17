package com.example.hertz.controllers;

import com.example.hertz.models.User;
import com.example.hertz.services.UserService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public List<User> getAll(){
        return userService.getAll();
    }

    @PostMapping()
    public User create(
            @Validated(User.Create.class)
            @RequestBody User user){
        return userService.create(user);
    }
}
