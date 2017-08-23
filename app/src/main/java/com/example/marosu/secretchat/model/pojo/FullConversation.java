package com.example.marosu.secretchat.model.pojo;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Relation;

import com.example.marosu.secretchat.model.db.entity.Conversation;
import com.example.marosu.secretchat.model.db.entity.Message;
import com.example.marosu.secretchat.model.db.entity.User;

import java.util.List;

/**
 * Created by Marius-Andrei Rosu on 8/23/2017.
 */
public class FullConversation {
    @Embedded
    private Conversation conversation;

    @Relation(parentColumn = "id", entityColumn = "conversation_id", entity = Message.class)
    private List<Message> messages;

    @Relation(parentColumn = "id", entityColumn = "user_id", entity = User.class)
    private List<String> participantIds;
}
