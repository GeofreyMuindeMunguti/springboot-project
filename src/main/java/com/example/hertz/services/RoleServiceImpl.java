package com.example.hertz.services;

import com.example.hertz.models.Role;
import com.example.hertz.repositories.RoleRepository;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

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
