package com.example.marosu.secretchat.model;

import java.util.Date;

/**
 * Created by Marius-Andrei Rosu on 8/7/2017.
 */
public class Message {
    private String id;
    private String text;
    private Date time;
    private String senderId;

    public String getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public Date getTime() {
        return time;
    }

    public String getSenderId() {
        return senderId;
    }
}
