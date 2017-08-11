package com.example.marosu.secretchat.model.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Marius-Andrei Rosu on 8/7/2017.
 */
public class Message {
    private String id;

    private String content;

    private String senderId;

    @SerializedName("createdDtm")
    private long timestamp;

    public String getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public String getSenderId() {
        return senderId;
    }

    public long getTimestamp() {
        return timestamp;
    }
}
