package com.example.marosu.secretchat.conversations;

import com.example.marosu.secretchat.base.BaseContract;
import com.example.marosu.secretchat.model.pojo.FullConversation;

import java.util.List;

/**
 * Created by Marius-Andrei Rosu on 8/7/2017.
 */
public interface ConversationsView extends BaseContract.View {
    void onConversationsLoaded(List<FullConversation> conversations);

    void onConversationsEmpty();

    void onConversationsFailed();
}
