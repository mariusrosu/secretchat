package com.example.marosu.secretchat.model.body;

/**
 * Created by Marius-Andrei Rosu on 8/28/2017.
 */
public class LoginBody {
    private String email;
    private String password;

    public LoginBody(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
