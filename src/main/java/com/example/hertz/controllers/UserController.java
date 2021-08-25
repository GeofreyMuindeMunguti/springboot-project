package com.example.hertz.controllers;

import com.example.hertz.models.Role;
import com.example.hertz.models.User;
import com.example.hertz.services.RoleService;
import com.example.hertz.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "users")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping()
    public Page<User> getAll(@RequestParam(defaultValue = "0") Integer page){
        return userService.getAll(page);
    }

    @PostMapping(value="register")
    public User create(
            @Validated(User.Create.class)
            @RequestBody User user){
        String hashed_password = passwordEncoder.encode(user.getPassword());
        Role admin_user_role = roleService.getByName("ADMIN");
        user.setPassword(hashed_password);
        user.roles.add(admin_user_role);
        return userService.create(user);
    }

    @PostMapping(value="home")
    public String Home(){
        return "Hey home";
    }
}
