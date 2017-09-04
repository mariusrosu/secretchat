package com.example.marosu.secretchat.model.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Marius-Andrei Rosu on 8/7/2017.
 */
public class Conversation {
    private String id;
    @SerializedName("createdDtm")
    private long timestamp;
    private List<Message> messages;
    private List<User> participants;

    public Conversation(String id, long timestamp, List<Message> messages, List<User> participants) {
        this.id = id;
        this.timestamp = timestamp;
        this.messages = messages;
        this.participants = participants;
    }

    public String getId() {
        return id;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public List<User> getParticipants() {
        return participants;
    }
}
