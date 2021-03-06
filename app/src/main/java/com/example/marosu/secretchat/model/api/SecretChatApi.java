package com.example.marosu.secretchat.model.api;

import com.example.marosu.secretchat.model.body.ConversationBody;
import com.example.marosu.secretchat.model.body.LoginBody;
import com.example.marosu.secretchat.model.body.MessageBody;
import com.example.marosu.secretchat.model.body.RegisterBody;
import com.example.marosu.secretchat.model.entity.Conversation;
import com.example.marosu.secretchat.model.entity.Message;
import com.example.marosu.secretchat.model.entity.User;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.Response;
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
    Observable<List<Conversation>> getConversations(@Query("userId") String userId);

    @GET("conversations/{conversationId}")
    Observable<Conversation> getConversation(@Path("conversationId") String conversationId);

    @POST("conversations")
    Observable<Conversation> createConversation(@Body ConversationBody body);

    @POST("messages")
    Observable<Message> sendMessage(@Body MessageBody body);

    @POST("login")
    Observable<Response<Void>> login(@Body LoginBody body);

    @POST("users/sign-up")
    Observable<User> register(@Body RegisterBody register);

    @GET("users/search")
    Observable<List<User>> searchUsers(@Query("searchQuery") CharSequence searchQuery);
}
