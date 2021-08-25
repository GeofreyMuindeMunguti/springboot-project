package com.example.hertz.services;

import com.example.hertz.models.Role;
import com.example.hertz.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role create(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Role getByName(String name) {
        System.out.println(name);
        return roleRepository.findByName(name);
    }
}
