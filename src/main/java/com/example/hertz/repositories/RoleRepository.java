package com.example.hertz.repositories;

import com.example.hertz.models.Role;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

public interface RoleRepository extends PagingAndSortingRepository<Role, UUID> {

    Role findByName(String name);

}
