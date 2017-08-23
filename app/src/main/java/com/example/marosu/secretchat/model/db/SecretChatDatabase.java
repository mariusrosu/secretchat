package com.example.marosu.secretchat.model.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.marosu.secretchat.model.db.dao.ConversationDao;
import com.example.marosu.secretchat.model.db.dao.MessageDao;
import com.example.marosu.secretchat.model.db.dao.ParticipantDao;
import com.example.marosu.secretchat.model.db.dao.UserDao;
import com.example.marosu.secretchat.model.db.entity.Conversation;
import com.example.marosu.secretchat.model.db.entity.Message;
import com.example.marosu.secretchat.model.db.entity.User;

/**
 * Created by Marius-Andrei Rosu on 8/21/2017.
 */
@Database(entities = {User.class, Conversation.class, Message.class}, version = 2)
public abstract class SecretChatDatabase extends RoomDatabase {
    public abstract UserDao userDao();

    public abstract ConversationDao conversationDao();

    public abstract ParticipantDao participantDao();

    public abstract MessageDao messageDao();
}
