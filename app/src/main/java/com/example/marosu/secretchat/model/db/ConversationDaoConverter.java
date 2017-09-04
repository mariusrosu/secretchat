package com.example.marosu.secretchat.model.db;

import android.arch.persistence.room.TypeConverter;

import com.example.marosu.secretchat.model.entity.Conversation;
import com.example.marosu.secretchat.model.entity.Message;
import com.example.marosu.secretchat.model.entity.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

/**
 * Created by Marius-Andrei Rosu on 8/30/2017.
 */
public class ConversationDaoConverter {

    @TypeConverter
    public static ConversationField objectToField(Conversation conversation, Gson gson) {
        final String messages = gson.toJson(conversation.getMessages());
        final String participants = gson.toJson(conversation.getParticipants());
        return new ConversationField(conversation.getId(), conversation.getTimestamp(),
                new ConversationContent(messages, participants));
    }

    @TypeConverter
    public static Conversation fieldToObject(ConversationField field, Gson gson) {
        final List<Message> messages = gson.fromJson(field.getContent().getMessages(),
                new TypeToken<List<Message>>() {
                }.getType());
        final List<User> participants = gson.fromJson(field.getContent().getParticipants(),
                new TypeToken<List<User>>() {
                }.getType());
        return new Conversation(field.getId(), field.getTimestamp(), messages, participants);
    }
}
