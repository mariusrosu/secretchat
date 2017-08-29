package com.example.marosu.secretchat.search

import com.example.marosu.secretchat.base.BaseContract
import com.example.marosu.secretchat.model.entity.Conversation
import com.example.marosu.secretchat.model.entity.User

/**
 * Created by Marius-Andrei Rosu on 8/29/2017.
 */
interface SearchView : BaseContract.View {

    fun onUsersLoaded(users: List<User>)

    fun onUsersFailed(throwable: Throwable)

    fun onConversationCreated(conversation: Conversation)

    fun onConversationFailed(throwable: Throwable)
}
