package com.example.HomeFinances.Models;

public class LoginResponse {
    private String Username;
    private String Token;

    public LoginResponse(String username, String token) {
        Username = username;
        Token = token;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getToken() {
        return Token;
    }

    public void setToken(String token) {
        Token = token;
    }
}
