package com.example.marosu.secretchat.model.api;

import com.example.marosu.secretchat.model.api.body.ConversationBody;
import com.example.marosu.secretchat.model.api.body.MessageBody;
import com.example.marosu.secretchat.model.api.response.ConversationResponse;
import com.example.marosu.secretchat.model.body.LoginBody;
import com.example.marosu.secretchat.model.db.entity.Conversation;
import com.example.marosu.secretchat.model.db.entity.Message;
import com.example.marosu.secretchat.model.db.entity.User;

import java.util.List;

import io.reactivex.Observable;
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
    Observable<List<ConversationResponse>> getConversations(@Query("userId") String userId);

    @GET("conversations/{conversationId}")
    Observable<Conversation> getConversation(@Path("conversationId") String conversationId);

    @POST("conversations")
    Observable<Conversation> createConversation(@Body ConversationBody body);

    @POST("messages")
    Observable<Message> sendMessage(@Body MessageBody body);

    @POST("users/login")
    Observable<Object> login(@Body LoginBody body);

    @GET("users/search")
    Observable<List<User>> searchUsers(@Query("searchQuery") String searchQuery);
}
