package com.example.marosu.secretchat.messages;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.example.marosu.secretchat.Session;
import com.example.marosu.secretchat.base.BasePresenter;
import com.example.marosu.secretchat.model.api.SecretChatApi;
import com.example.marosu.secretchat.model.api.SecretChatClient;
import com.example.marosu.secretchat.model.api.body.MessageBody;
import com.example.marosu.secretchat.model.db.Database;
import com.example.marosu.secretchat.model.db.SecretChatDatabase;
import com.example.marosu.secretchat.model.db.entity.Conversation;
import com.example.marosu.secretchat.model.db.entity.Message;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static com.example.marosu.secretchat.messages.MessagesActivity.CONVERSATION_EXTRA;
import static com.example.marosu.secretchat.messages.MessagesActivity.CONVERSATION_ID_EXTRA;

/**
 * Created by Marius-Andrei Rosu on 8/7/2017.
 */
public final class MessagesPresenter extends BasePresenter<MessagesView> {
    private SecretChatApi api;
    private SecretChatDatabase db;
    private Conversation conversation;

    public MessagesPresenter(Context context) {
        api = SecretChatClient.createApi();
        db = Database.getSecretChatDatabase(context);
    }

    public void handleExtras(Bundle extras) {
        if (extras != null) {
            final String conversationId = extras.getParcelable(CONVERSATION_ID_EXTRA);
            getView().onConversationLoaded(conversation);
            getView().setConversationTitle(conversation.getParticipants().get(0).getFullName());
        }
    }

    public void getMessages() {
        disposables.add(db.messageDao().getAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        messages -> Log.d("Debugging", "getDbMessages() -> onSuccess() -> messages.size() = " + messages.size()),
                        throwable -> Log.d("Debugging", "getDbMessages() -> onError() -> t = " + throwable.getStackTrace())));
    }

    public void refresh() {
        disposables.add(api.getConversation(conversation.getId())
                .compose(applySchedulers())
                .doOnError(throwable -> getView().onConversationFailed())
                .subscribe(conversation -> getView().onConversationLoaded(conversation)));
    }

    private void saveMessages(List<Message> messages) {
        disposables.add(Observable.fromCallable(() -> db.messageDao().insertAll(messages))
                .compose(applySchedulers())
                .subscribe());
    }

    public void sendMessage(String content) {
        final MessageBody body
                = new MessageBody(conversation.getId(), Session.getSession().getUserId(), content);

        final Message newMessage = new Message.Builder()
                .setTimestamp(System.currentTimeMillis())
                .isSending(true)
                .setBody(body)
                .build();

        disposables.add(api.sendMessage(body)
                .compose(applySchedulers())
                .subscribe(
                        sentMessage -> getView().onMessageSent(sentMessage),
                        throwable -> getView().onMessageFailed()));
    }
}
