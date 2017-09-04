package com.example.marosu.secretchat.model.db;

import android.arch.persistence.room.Room;
import android.content.Context;

/**
 * Created by Marius-Andrei Rosu on 8/21/2017.
 */
public class Database {
    private static final String DATABASE_NAME = "secret_chat_db";
    private static SecretChatDatabase secretChatDb;

    private Database() {
        //Private constructor
    }

    public static synchronized SecretChatDatabase getSecretChatDatabase(Context context) {
        if (secretChatDb == null) {
            secretChatDb = Room.databaseBuilder(context, SecretChatDatabase.class, DATABASE_NAME).build();
        }
        return secretChatDb;
    }
}
