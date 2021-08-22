package com.example.hertz.services;

import com.example.hertz.models.Role;

import java.util.List;

public interface RoleService {

    Role create(Role role);

    Role getByName(String name);
}
