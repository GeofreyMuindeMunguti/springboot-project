package com.example.hertz.repositories;

import com.example.hertz.models.User;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

public interface UserRepository extends PagingAndSortingRepository<User, UUID> {

    User findByUsername(String username);

}
