 package com.example.hertz.models;
import javax.validation.constraints.NotNull;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private UUID Id;


    @Column(name= "username", unique=true)
    @NotNull(groups = User.Create.class,
            message = "Username is required")
    private String username;

    @Column(name="hashed_password")
    @NotNull(groups = User.Create.class,
            message = "Username is required")
    private String password;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    public Set<Role> roles = new HashSet();

    public boolean isEnabled;

    public UUID getId() {
        return Id;
    }

    public String getUsername() {
        return username;
    }


    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }


    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public interface Create{}
    public interface Update{}

    @Override
    public String toString() {
        return "User{" +
                "Id=" + Id +
                ", username='" + username + '\'' +
                '}';
    }
}
