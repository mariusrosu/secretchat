package com.example.marosu.secretchat.search

import com.example.marosu.secretchat.Session
import com.example.marosu.secretchat.base.BasePresenter
import com.example.marosu.secretchat.model.api.SecretChatApi
import com.example.marosu.secretchat.model.api.SecretChatClient
import com.example.marosu.secretchat.model.body.ConversationBody
import com.example.marosu.secretchat.model.entity.User
import java.util.concurrent.TimeUnit

/**
 * Created by Marius-Andrei Rosu on 8/29/2017.
 */
class SearchPresenter : BasePresenter<SearchView>() {
    private val api: SecretChatApi = SecretChatClient.createApi()

    fun searchUsers(input: CharSequence) {
        disposables.add(api.searchUsers(input)
                .compose(applySchedulers())
                .debounce(300, TimeUnit.MILLISECONDS)
                .doOnError { error -> view.onUsersFailed(error) }
                .subscribe { users -> view.onUsersLoaded(users) })
    }

    fun createConversation(user: User) {
        val userId = Session.getSession().userId
        val body = ConversationBody(userId, listOf(user.id, userId))

        disposables.add(api.createConversation(body)
                .compose(applySchedulers())
                .doOnError { error -> view.onConversationFailed(error) }
                .subscribe { conversation -> view.onConversationCreated(conversation) })
    }
}
