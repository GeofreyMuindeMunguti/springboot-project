package com.example.hertz.repositories;

import com.example.hertz.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.awt.print.Pageable;

public interface UserRepository extends PagingAndSortingRepository<User, Long> {


    User findByUsername(String username);

}
