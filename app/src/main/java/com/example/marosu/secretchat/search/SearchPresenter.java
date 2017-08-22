package com.example.marosu.secretchat.search;

import com.example.marosu.secretchat.Session;
import com.example.marosu.secretchat.base.BasePresenter;
import com.example.marosu.secretchat.model.api.SecretChatApi;
import com.example.marosu.secretchat.model.api.SecretChatClient;
import com.example.marosu.secretchat.model.body.ConversationBody;
import com.example.marosu.secretchat.model.entity.User;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * Created by Marius-Andrei Rosu on 8/17/2017.
 */
public final class SearchPresenter extends BasePresenter<SearchView> {
    private SecretChatApi api;

    public SearchPresenter() {
        this.api = SecretChatClient.createApi();
    }

    public void searchUsers(CharSequence input) {
        disposables.add(api.searchUsers(input.toString())
                .debounce(300, TimeUnit.MILLISECONDS)
                .compose(applySchedulers())
                .subscribe(
                        users -> getView().onUsersLoaded(users),
                        throwable -> getView().onUsersFailed(throwable)));
    }

    public void createConversation(User user) {
        final String userId = Session.getSession().getUserId();
        final ConversationBody conversationBody = new ConversationBody(userId, Arrays.asList(user.getId(), userId));

        disposables.add(api.createConversation(conversationBody)
                .compose(applySchedulers())
                .subscribe(
                        conversation -> getView().onConversationCreated(conversation),
                        throwable -> getView().onConversationFailed(throwable)));
    }
}
