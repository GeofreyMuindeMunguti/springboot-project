package com.example.hertz.schemas;

import com.example.hertz.models.Role;

import javax.persistence.Entity;
import java.util.Set;

public class UserLoginResponse {
    private String username;
    private String token;
    private Set<Role> roles;


    public void setUsername(String username) {
        this.username = username;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setRole(Set<Role> roles) {
        this.roles = roles;
    }

    public String getUsername() {
        return username;
    }

    public String getToken() {
        return token;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public UserLoginResponse(String username, String token, Set<Role> roles) {
        this.username = username;
        this.token = token;
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "UserLoginResponse{" +
                "username='" + username + '\'' +
                ", token='" + token + '\'' +
                ", roles=" + roles +
                '}';
    }
}
