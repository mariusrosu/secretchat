package com.example.marosu.secretchat.model.api.response;

import com.example.marosu.secretchat.model.db.entity.Message;
import com.example.marosu.secretchat.model.db.entity.User;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Marius-Andrei Rosu on 8/23/2017.
 */
public class ConversationResponse {
    @SerializedName("id")
    private String id;

    @SerializedName("createdDtm")
    private long timestamp;

    @SerializedName("userId")
    private String userId;

    @SerializedName("messages")
    private List<Message> messages;

    @SerializedName("participants")
    private List<User> users;

    public String getId() {
        return id;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getUserId() {
        return userId;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public List<User> getUsers() {
        return users;
    }
}
