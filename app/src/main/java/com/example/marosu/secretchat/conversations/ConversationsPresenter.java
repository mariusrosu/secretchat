package com.example.marosu.secretchat.conversations;

import android.util.Log;

import com.example.marosu.secretchat.Session;
import com.example.marosu.secretchat.base.BasePresenter;
import com.example.marosu.secretchat.model.api.SecretChatApi;
import com.example.marosu.secretchat.model.api.SecretChatClient;
import com.example.marosu.secretchat.model.entity.Conversation;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Single;

/**
 * Created by Marius-Andrei Rosu on 8/7/2017.
 */
public final class ConversationsPresenter extends BasePresenter<ConversationsView> {
    private SecretChatApi api;

    public ConversationsPresenter() {
        this.api = SecretChatClient.createApi();
    }

    public void getConversations() {
        disposables.add(api.getConversations(Session.getSession().getUserId())
                .compose(applySchedulers())
                .subscribe(
                        conversations -> handleConversations(conversations),
                        throwable -> handleConversationError(throwable)));
    }

    private void handleConversations(List<Conversation> conversations) {
        Log.d("Debugging", "onSuccess(): value = " + conversations);
        if (conversations == null || conversations.isEmpty()) {
            getView().onConversationsEmpty();
        } else {
            getView().onConversationsLoaded(conversations);
        }
    }

    private void handleConversationError(Throwable throwable) {
        Log.e("Debugging", "onError(): throwable = " + throwable.getMessage());
        getView().onConversationsFailed();
    }

    //TODO: Remove this after the backend is ready.
    private Single<List<Conversation>> getData() {
        final String conversationsJson = getView().getConversationsJson();
        Log.d("Debugging", conversationsJson);
        final List<Conversation> conversations = new Gson().fromJson(conversationsJson,
                new TypeToken<List<Conversation>>() {
                }.getType());
        return Single.just(conversations).delay(2000, TimeUnit.MILLISECONDS);
    }
}
