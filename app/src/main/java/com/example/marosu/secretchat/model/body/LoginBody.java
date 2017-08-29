package com.example.marosu.secretchat.model.body;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Marius-Andrei Rosu on 8/28/2017.
 */
public class LoginBody {
    @SerializedName("email")
    private String email;

    @SerializedName("password")
    private String password;

    public LoginBody(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
