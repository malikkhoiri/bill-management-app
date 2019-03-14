package com.code5.kb5.model.login;

public class LoginParam {
    private String email;
    private String password;

    public LoginParam() {
    }

    public LoginParam(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
