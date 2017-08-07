package com.example.marosu.secretchat.chatList;

import com.example.marosu.secretchat.base.BaseContract;
import com.example.marosu.secretchat.model.Conversation;

import java.util.List;

/**
 * Created by Marius-Andrei Rosu on 8/7/2017.
 */
public interface ChatListView extends BaseContract.View {
    void onConversationsLoaded(List<Conversation> conversations);
}
