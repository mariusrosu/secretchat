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
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Marius-Andrei Rosu on 8/7/2017.
 */
public class ConversationsPresenter extends BasePresenter<ConversationsView> {
    private SecretChatApi api;

    public ConversationsPresenter() {
        this.api = SecretChatClient.createApi();
    }

    public void getConversations() {
        api.getConversations(Session.getSession().getUserId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(conversations -> {
                    Log.d("Debugging", "onSuccess(): value = " + conversations);
                    if (conversations == null || conversations.isEmpty()) {
                        getView().onConversationsEmpty();
                    } else {
                        getView().onConversationsLoaded(conversations);
                    }
                }, throwable -> {
                    Log.d("Debugging", "onError(): e = " + throwable);
                    getView().onConversationsFailed();
                });
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
