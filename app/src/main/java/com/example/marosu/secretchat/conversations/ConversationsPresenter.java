package com.example.marosu.secretchat.conversations;

import android.util.Log;

import com.example.marosu.secretchat.base.BasePresenter;
import com.example.marosu.secretchat.model.SecretChatApi;
import com.example.marosu.secretchat.model.SecretChatClient;
import com.example.marosu.secretchat.model.entity.Conversation;

import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
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
        api.getAllConversations()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new SingleObserver<List<Conversation>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d("Debugging", "onSubscribe(): d = " + d);
                    }

                    @Override
                    public void onSuccess(List<Conversation> conversations) {
                        Log.d("Debugging", "onNext(): value = " + conversations);
                        if (conversations == null || conversations.isEmpty()) {
                            getView().onConversationsEmpty();
                        } else {
                            getView().onConversationsLoaded(conversations);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("Debugging", "onError(): e = " + e);
                        getView().onConversationsFailed();
                    }
                });
    }

    @Override
    public void onPresenterDestroy() {
        detachView();
    }
}
