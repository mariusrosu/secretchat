package com.example.marosu.secretchat.model.entity;

/**
 * Created by Marius-Andrei Rosu on 8/7/2017.
 */
public class User {
    private String id;
    private String firstName;
    private String lastName;
    private String email;

    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }
}
