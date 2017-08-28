package com.example.marosu.secretchat.conversations;

import android.util.Log;

import com.example.marosu.secretchat.Session;
import com.example.marosu.secretchat.base.BasePresenter;
import com.example.marosu.secretchat.model.api.SecretChatApi;
import com.example.marosu.secretchat.model.api.SecretChatClient;
import com.example.marosu.secretchat.model.entity.Conversation;
import com.example.marosu.secretchat.model.entity.User;
import com.example.marosu.secretchat.util.comparator.ConversationComparator;
import com.example.marosu.secretchat.util.comparator.UserComparator;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Marius-Andrei Rosu on 8/7/2017.
 */
public final class ConversationsPresenter extends BasePresenter<ConversationsView> {
    private SecretChatApi api;
    private Comparator<User> userComparator;
    private Comparator<Conversation> conversationComparator;

    public ConversationsPresenter() {
        this.api = SecretChatClient.createApi();
        this.userComparator = new UserComparator();
        this.conversationComparator = new ConversationComparator();
    }

    public void getConversations() {
        disposables.add(api.getConversations(Session.getSession().getUserId())
                .map(conversations -> mapConversations(conversations))
                .compose(applySchedulers())
                .doOnError(throwable -> handleConversationError(throwable))
                .subscribe(conversations -> handleConversations(conversations)));
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

    private List<Conversation> mapConversations(List<Conversation> conversations) {
        for (Conversation conversation : conversations) {
            Collections.sort(conversation.getParticipants(), userComparator);
        }
        Collections.sort(conversations, conversationComparator);
        return conversations;
    }
}
