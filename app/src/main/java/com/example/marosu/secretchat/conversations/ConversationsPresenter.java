package com.example.marosu.secretchat.conversations;

import android.content.Context;
import android.util.Log;

import com.example.marosu.secretchat.Session;
import com.example.marosu.secretchat.base.BasePresenter;
import com.example.marosu.secretchat.model.api.SecretChatApi;
import com.example.marosu.secretchat.model.api.SecretChatClient;
import com.example.marosu.secretchat.model.api.response.ConversationResponse;
import com.example.marosu.secretchat.model.db.Database;
import com.example.marosu.secretchat.model.db.SecretChatDatabase;
import com.example.marosu.secretchat.model.db.entity.Conversation;
import com.example.marosu.secretchat.model.db.entity.Message;
import com.example.marosu.secretchat.model.db.entity.Participant;
import com.example.marosu.secretchat.model.db.entity.User;
import com.example.marosu.secretchat.util.comparator.ConversationComparator;
import com.example.marosu.secretchat.util.comparator.UserComparator;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import io.reactivex.Observable;

/**
 * Created by Marius-Andrei Rosu on 8/7/2017.
 */
public final class ConversationsPresenter extends BasePresenter<ConversationsView> {
    private SecretChatApi api;
    private SecretChatDatabase db;
    private Comparator<User> userComparator;
    private Comparator<Conversation> conversationComparator;

    public ConversationsPresenter(Context context) {
        this.api = SecretChatClient.createApi();
        this.db = Database.getSecretChatDatabase(context);
        this.userComparator = new UserComparator();
        this.conversationComparator = new ConversationComparator();
    }

    public void getConversations() {
        disposables.add(api.getConversations(Session.getSession().getUserId())
                .doOnNext(response -> storeConverations(response))
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

    private void storeConverations(List<ConversationResponse> conversationResponses) {
        for (ConversationResponse response : conversationResponses) {
        }
    }

    private void saveConversations(List<Conversation> conversations) {
        disposables.add(Observable.fromCallable(() -> db.conversationDao().insertAll(conversations))
                .compose(applySchedulers())
                .subscribe());
    }

    private void saveUsers(List<User> users) {
        disposables.add(Observable.fromCallable(() -> db.userDao().insertAll(users))
                .compose(applySchedulers())
                .subscribe());
    }

    private void saveParticipants(List<Participant> participants) {
        disposables.add(Observable.fromCallable(() -> db.participantDao().insertAll(participants))
                .compose(applySchedulers())
                .subscribe());
    }

    private void saveMessages(List<Message> messages) {
        disposables.add(Observable.fromCallable(() -> db.messageDao().insertAll(messages))
                .compose(applySchedulers())
                .subscribe());
    }
}
