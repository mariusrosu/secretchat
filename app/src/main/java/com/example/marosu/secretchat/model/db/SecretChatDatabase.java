package com.example.marosu.secretchat.model.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
<<<<<<< Updated upstream

import com.example.marosu.secretchat.model.db.dao.ConversationDao;
import com.example.marosu.secretchat.model.db.dao.MessageDao;
import com.example.marosu.secretchat.model.db.dao.ParticipantDao;
import com.example.marosu.secretchat.model.db.dao.UserDao;
import com.example.marosu.secretchat.model.db.entity.Conversation;
import com.example.marosu.secretchat.model.db.entity.Message;
import com.example.marosu.secretchat.model.db.entity.User;
=======
import android.arch.persistence.room.TypeConverters;
>>>>>>> Stashed changes

/**
 * Created by Marius-Andrei Rosu on 8/21/2017.
 */
<<<<<<< Updated upstream
@Database(entities = {User.class, Conversation.class, Message.class}, version = 2)
public abstract class SecretChatDatabase extends RoomDatabase {
    public abstract UserDao userDao();

    public abstract ConversationDao conversationDao();

    public abstract ParticipantDao participantDao();

    public abstract MessageDao messageDao();
=======
@TypeConverters(ConversationDaoConverter.class)
@Database(entities = ConversationField.class, version = 1)
public abstract class SecretChatDatabase extends RoomDatabase {
    public abstract ConversationDao conversationDao();
>>>>>>> Stashed changes
}
