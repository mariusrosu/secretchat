package com.example.marosu.secretchat.messages;

import android.os.Bundle;

import com.example.marosu.secretchat.base.BasePresenter;
import com.example.marosu.secretchat.model.entity.Conversation;

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

    @Override
    public void onPresenterDestroy() {

    }
}
