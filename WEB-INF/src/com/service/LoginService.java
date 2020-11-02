package com.gerald.service;

public class LoginService {
    public boolean authenticate(String username, String password) {
        if (password == null || password.trim() == "") {
            return false;
        }
        return true;
    }
}