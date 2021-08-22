package com.example.hertz.repositories;

import com.example.hertz.models.AuthToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AuthTokenRepository extends JpaRepository<AuthToken, UUID> {
}
