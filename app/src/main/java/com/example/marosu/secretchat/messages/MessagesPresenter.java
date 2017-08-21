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

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
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

    public void getMessages() {
        db.messageDao().getAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(messages -> Log.d("Debugging", "getDbMessages() -> onSuccess() -> messages.size() = " + messages.size()),
                        throwable -> Log.d("Debugging", "getDbMessages() -> onError() -> t = " + throwable.getStackTrace()));
    }

    public void refresh() {
        disposables.add(api.getConversation(conversation.getId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(conversation -> getView().onConversationLoaded(conversation),
                        throwable -> getView().onConversationFailed()));
    }

    private void saveMessages(List<Message> messages) {
        Observable.fromCallable(() -> db.messageDao().insertAll(messages))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }

    public void sendMessage(String content) {
        final Message newMessage = new Message.Builder()
                .setConversationId(conversation.getId())
                .setSenderId(Session.getSession().getUserId())
                .setTimestamp(System.currentTimeMillis())
                .setContent(content)
                .isSending(true)
                .build();

        api.sendMessage(newMessage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(sentMessage -> getView().onMessageSent(sentMessage),
                        throwable -> getView().onMessageFailed());
    }
}
