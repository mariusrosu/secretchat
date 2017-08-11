package com.example.marosu.secretchat.model;

import com.example.marosu.secretchat.model.entity.Conversation;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Marius-Andrei Rosu on 8/9/2017.
 */
public interface SecretChatApi {
    @GET("conversations")
    Single<List<Conversation>> getAllConversations();

    @GET("conversations")
    Observable<Object> getConversation(@Query("userId") String userId);
}
