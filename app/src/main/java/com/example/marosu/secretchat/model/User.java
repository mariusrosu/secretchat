package com.example.marosu.secretchat.model;

import java.util.List;

/**
 * Created by Marius-Andrei Rosu on 8/7/2017.
 */
public class User {
    private String id;
    private String email;
    private List<Conversation> conversations;

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public List<Conversation> getConversations() {
        return conversations;
    }
}
