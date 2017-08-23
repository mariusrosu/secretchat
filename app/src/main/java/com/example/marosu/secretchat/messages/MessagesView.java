package com.example.marosu.secretchat.messages;

import com.example.marosu.secretchat.base.BaseContract;
import com.example.marosu.secretchat.model.db.entity.Conversation;
import com.example.marosu.secretchat.model.db.entity.Message;

/**
 * Created by Marius-Andrei Rosu on 8/7/2017.
 */
public interface MessagesView extends BaseContract.View {
    void setConversationTitle(String title);

    void onConversationLoaded(Conversation conversation);

    void onConversationFailed();

    void onMessageSent(Message message);

    void onMessageFailed();
}
