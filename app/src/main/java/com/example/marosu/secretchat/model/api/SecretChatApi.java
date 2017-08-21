package com.example.marosu.secretchat.model.api;

import com.example.marosu.secretchat.model.entity.Conversation;
import com.example.marosu.secretchat.model.entity.Message;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Marius-Andrei Rosu on 8/9/2017.
 */
public interface SecretChatApi {
    @GET("conversations")
    Single<List<Conversation>> getAllConversations();

    @GET("conversations")
    Single<List<Conversation>> getConversations(@Query("userId") String userId);

    @GET("conversations/{conversationId}")
    Single<Conversation> getConversation(@Path("conversationId") String conversationId);

    @POST("messages")
    Single<Message> sendMessage(@Body Message message);
}
