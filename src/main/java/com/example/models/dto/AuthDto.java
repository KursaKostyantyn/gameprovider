package com.example.models.dto;

public class AuthDto {
    private String login;
    private String password;

    public AuthDto(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public AuthDto() {
    }

    public String getLogin() {
        return login;
    }

    @Override
    public String toString() {
        return "AuthDto{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
