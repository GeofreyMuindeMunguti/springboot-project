package com.example.hertz.controllers;

import com.example.hertz.models.Role;
import com.example.hertz.models.User;
import com.example.hertz.services.RoleService;
import com.example.hertz.services.UserService;
import org.springframework.data.domain.Page;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(value = "users")
public class UserController {

    private final UserService userService;

    private final RoleService roleService;


    private final PasswordEncoder passwordEncoder;

    public UserController(UserService userService, RoleService roleService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }


    @Secured({"ROLE_ADMIN"})
    @GetMapping()
    public Page<User> getAll(@RequestParam(defaultValue = "0") Integer page){
        return userService.getAll(page);
    }

    @PostMapping("/activate")
    public User activate(@RequestParam UUID id) {
        User user = userService.getById(id);
        return userService.activate(user);
    }

    @GetMapping("/me")
    public User loggedInUserDetails(){
        return userService.instance();
    }

    @PostMapping(value="register")
    public User create(
            @Validated(User.Create.class)
            @RequestBody User user){
        java.lang.String hashed_password = passwordEncoder.encode(user.getPassword());
        Role base_user_role = roleService.getByName("ROLE_USER");

        user.setPassword(hashed_password);
        user.roles.add(base_user_role);
        return userService.create(user);
    }

    @PostMapping(value="home")
    public String Home(){
        return "Hey home";
    }
}
