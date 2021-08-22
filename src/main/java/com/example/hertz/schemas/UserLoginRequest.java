package com.example.hertz.schemas;

public class UserLoginRequest {
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }


    public String getPassword() {
        return password;
    }

    public UserLoginRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

}
