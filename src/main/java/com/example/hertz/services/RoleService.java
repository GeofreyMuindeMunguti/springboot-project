package com.example.hertz.services;

import com.example.hertz.models.Role;

public interface RoleService {

    Role create(Role role);

    Role getByName(String name);
}
