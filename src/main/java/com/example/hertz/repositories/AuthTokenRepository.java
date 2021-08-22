package com.example.hertz.repositories;

import com.example.hertz.models.AuthToken;
import com.example.hertz.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface AuthTokenRepository extends JpaRepository<AuthToken, UUID> {
    List<AuthToken> findByUser(User user);
}
