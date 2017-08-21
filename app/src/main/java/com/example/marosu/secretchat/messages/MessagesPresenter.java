package com.example.marosu.secretchat.messages;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.example.marosu.secretchat.Session;
import com.example.marosu.secretchat.base.BasePresenter;
import com.example.marosu.secretchat.model.api.SecretChatApi;
import com.example.marosu.secretchat.model.api.SecretChatClient;
import com.example.marosu.secretchat.model.db.Database;
import com.example.marosu.secretchat.model.db.SecretChatDatabase;
import com.example.marosu.secretchat.model.entity.Conversation;
import com.example.marosu.secretchat.model.entity.Message;

import java.util.List;

import io.reactivex.MaybeObserver;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static com.example.marosu.secretchat.messages.MessagesActivity.CONVERSATION_EXTRA;

/**
 * Created by Marius-Andrei Rosu on 8/7/2017.
 */
public class MessagesPresenter extends BasePresenter<MessagesView> {
    private SecretChatApi api;
    private SecretChatDatabase db;
    private Conversation conversation;

    public MessagesPresenter(Context context) {
        api = SecretChatClient.createApi();
        db = Database.getSecretChatDatabase(context);
    }

    public void handleExtras(Bundle extras) {
        if (extras != null) {
            final Conversation conversation = extras.getParcelable(CONVERSATION_EXTRA);
            this.conversation = conversation;
            getView().onConversationLoaded(conversation);
            getView().setConversationTitle(conversation.getParticipants().get(0).getFullName());
        }
    }

    public void getDbMessages() {
        db.messageDao().getAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MaybeObserver<List<Message>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d("Debugging", "getDbMessages() -> onSubscribe()");
                    }

                    @Override
                    public void onSuccess(List<Message> messages) {
                        Log.d("Debugging", "getDbMessages() -> onSuccess() -> messages.size() = " + messages.size());
                    }

                    @Override
                    public void onError(Throwable t) {
                        Log.d("Debugging", "getDbMessages() -> onError()");
                    }

                    @Override
                    public void onComplete() {
                        Log.d("Debugging", "getDbMessages() -> onComplete()");
                    }
                });
    }

    public void refresh() {
        api.getConversation(conversation.getId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<Conversation>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(Conversation conversation) {
                        Log.d("Debugging", "onNext(): value = " + conversation);
                        getView().onConversationLoaded(conversation);
                        db.messageDao().insertAll(conversation.getMessages());
                    }

                    @Override
                    public void onError(Throwable e) {
                        getView().onConversationFailed();
                    }
                });
    }

    public void sendMessage(String content) {
        final Message message = new Message.Builder()
                .setConversationId(conversation.getId())
                .setSenderId(Session.getSession().getUserId())
                .setTimestamp(System.currentTimeMillis())
                .setContent(content)
                .isSending(true)
                .build();

        api.sendMessage(message)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<Message>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d("Debugging", "onSubscribe(): d = " + d);
                        disposables.add(d);
                    }

                    @Override
                    public void onSuccess(Message message) {
                        Log.d("Debugging", "onNext(): value = " + message);
                        getView().onMessageSent(message);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("Debugging", "onError(): e = " + e);
                        getView().onMessageFailed();
                    }
                });
    }

    @Override
    public void onPresenterDestroy() {

    }
}
