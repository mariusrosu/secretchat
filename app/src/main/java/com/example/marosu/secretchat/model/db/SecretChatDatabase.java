package com.example.marosu.secretchat.model.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.marosu.secretchat.model.entity.Message;

/**
 * Created by Marius-Andrei Rosu on 8/21/2017.
 */
@Database(entities = Message.class, version = 1)
public abstract class SecretChatDatabase extends RoomDatabase {
    public abstract MessageDao messageDao();
}
