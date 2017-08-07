package com.example.marosu.secretchat.model;

/**
 * Created by Marius-Andrei Rosu on 8/7/2017.
 */
public class Conversation {
    private String id;
    private User participant;
    private Message lastMessage;

    public String getId() {
        return id;
    }

    public User getParticipant() {
        return participant;
    }

    public Message getLastMessage() {
        return lastMessage;
    }
}
