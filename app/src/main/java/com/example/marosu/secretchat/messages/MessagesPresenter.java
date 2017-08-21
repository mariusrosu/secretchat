package com.example.marosu.secretchat.messages;

import android.os.Bundle;
import android.util.Log;

import com.example.marosu.secretchat.Session;
import com.example.marosu.secretchat.base.BasePresenter;
import com.example.marosu.secretchat.model.SecretChatApi;
import com.example.marosu.secretchat.model.SecretChatClient;
import com.example.marosu.secretchat.model.entity.Conversation;
import com.example.marosu.secretchat.model.entity.Message;

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
    private Conversation conversation;

    public MessagesPresenter() {
        api = SecretChatClient.createApi();
    }

    public void handleExtras(Bundle extras) {
        if (extras != null) {
            final Conversation conversation = extras.getParcelable(CONVERSATION_EXTRA);
            this.conversation = conversation;
            getView().onConversationLoaded(conversation);
            getView().setConversationTitle(conversation.getParticipants().get(0).getFullName());
        }
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
