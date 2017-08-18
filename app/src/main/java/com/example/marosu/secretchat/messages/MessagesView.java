package com.example.marosu.secretchat.messages;

import com.example.marosu.secretchat.base.BaseContract;
import com.example.marosu.secretchat.model.entity.Conversation;
import com.example.marosu.secretchat.model.entity.Message;

/**
 * Created by Marius-Andrei Rosu on 8/7/2017.
 */
public interface MessagesView extends BaseContract.View {
    void setConversationTitle(String title);

    void onConversationLoaded(Conversation conversation);

    void onMessageSent(Message message);
}
