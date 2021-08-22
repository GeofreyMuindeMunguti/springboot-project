package com.example.hertz.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name="auth_tokens")
public class AuthToken {

    @Id
    @Column(name="Id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name= "token")
    @NotNull(groups = AuthToken.Create.class, message="Token cannot be null")
    private String token;

    @Column(name="expiry_date")
    @NotNull(groups = AuthToken.Create.class, message="Expiry date cannot be null")
    private Date expiryDate;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }


    public UUID getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public AuthToken(String token, Date expiryDate, User user) {
        this.token = token;
        this.expiryDate = expiryDate;
        this.user = user;
    }

    public AuthToken() {
    }

    @Override
    public String toString() {
        return "AuthToken{" +
                "id=" + id +
                ", token='" + token + '\'' +
                ", user='" + user + '\'' +
                '}';
    }

    public interface Create {}
    public interface Update {}
}
