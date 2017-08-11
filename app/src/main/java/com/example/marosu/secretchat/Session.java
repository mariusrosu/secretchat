package com.example.marosu.secretchat;

/**
 * Created by Marius-Andrei Rosu on 8/11/2017.
 */
public class Session {
    private static Session session;
    private static final String userId = "ff8080815dc1be6b015dc1c3a9bb5423";

    private Session() {
        //Private constructor
    }

    public static synchronized Session getSession() {
        if (session == null) {
            session = new Session();
        }
        return session;
    }

    public String getUserId() {
        return userId;
    }
}
