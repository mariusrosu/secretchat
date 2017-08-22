package com.example.marosu.secretchat.search;

import com.example.marosu.secretchat.base.BaseContract;
import com.example.marosu.secretchat.model.entity.Conversation;
import com.example.marosu.secretchat.model.entity.User;

import java.util.List;

/**
 * Created by Marius-Andrei Rosu on 8/17/2017.
 */
public interface SearchView extends BaseContract.View {
    void onUsersLoaded(List<User> users);

    void onUsersFailed(Throwable throwable);

    void onConversationCreated(Conversation conversation);

    void onConversationFailed(Throwable throwable);
}
