package com.example.marosu.secretchat.messages;

import android.os.Bundle;

import com.example.marosu.secretchat.Session;
import com.example.marosu.secretchat.base.BasePresenter;
import com.example.marosu.secretchat.model.entity.Conversation;
import com.example.marosu.secretchat.model.entity.Message;

import static com.example.marosu.secretchat.messages.MessagesActivity.CONVERSATION_EXTRA;

/**
 * Created by Marius-Andrei Rosu on 8/7/2017.
 */
public class MessagesPresenter extends BasePresenter<MessagesView> {

    public void handleExtras(Bundle extras) {
        if (extras != null) {
            final Conversation conversation = extras.getParcelable(CONVERSATION_EXTRA);
            getView().onConversationLoaded(conversation);
            getView().setConversationTitle(conversation.getParticipants().get(0).getFullName());
        }
    }

    public void sendMessage(String content) {
        final Message message = new Message.Builder()
                .setContent(content)
                .setSenderId(Session.getSession().getUserId())
                .setTimestamp(System.currentTimeMillis())
                .build();
        //TODO: Send message to the API
        getView().onMessageSent(message);
    }

    @Override
    public void onPresenterDestroy() {

    }
}
