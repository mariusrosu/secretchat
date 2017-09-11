package com.example.marosu.secretchat.model.api

import com.example.marosu.secretchat.model.body.ConversationBody
import com.example.marosu.secretchat.model.body.LoginBody
import com.example.marosu.secretchat.model.body.MessageBody
import com.example.marosu.secretchat.model.entity.Conversation
import com.example.marosu.secretchat.model.entity.Message
import com.example.marosu.secretchat.model.entity.User
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.*

/**
 * Created by Marius-Andrei Rosu on 9/8/2017.
 */
interface SecretChatApi {

    @GET("conversations")
    fun getAllConversations(): Single<List<Conversation>>

    @GET("conversations")
    fun getConversations(@Query("userId") userId: String): Observable<List<Conversation>>

    @GET("conversations/{conversationId}")
    fun getConversation(@Path("conversationId") conversationId: String): Observable<Conversation>

    @POST("conversations")
    fun createConversation(@Body body: ConversationBody): Observable<Conversation>

    @POST("messages")
    fun sendMessage(@Body body: MessageBody): Observable<Message>

    @POST("users/login")
    fun login(@Body body: LoginBody): Observable<Any>

    @GET("users/search")
    fun searchUsers(@Query("searchQuery") searchQuery: String): Observable<List<User>>
}