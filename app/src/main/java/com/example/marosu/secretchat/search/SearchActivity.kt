package com.example.marosu.secretchat.search

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import com.example.marosu.secretchat.R
import com.example.marosu.secretchat.base.BaseActivity
import com.example.marosu.secretchat.messages.MessagesActivity
import com.example.marosu.secretchat.model.entity.Conversation
import com.example.marosu.secretchat.model.entity.User

/**
 * Created by Marius-Andrei Rosu on 8/29/2017.
 */
class SearchActivity : BaseActivity<SearchView, SearchPresenter>(), SearchView {
    private lateinit var adapter: SearchAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val searchInput = findViewById<EditText>(R.id.search_input)
        val searchList = findViewById<RecyclerView>(R.id.search_recycler)

        initializeAdapter()
        searchList.adapter = adapter
        searchList.setHasFixedSize(true)
        searchList.layoutManager = LinearLayoutManager(this)

        searchInput.addTextChangedListener(object : SearchInputWatcher() {
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s!!.length > 2) {
                    presenter.searchUsers(s)
                }
            }
        })
    }

    private fun initializeAdapter() {
        adapter = SearchAdapter(ArrayList<User>())
        adapter.clickSubject.subscribe({ user -> presenter.createConversation(user) })
    }

    override fun initPresenter() = SearchPresenter()

    override fun getLayoutId() = R.layout.activity_search

    override fun onUsersLoaded(users: List<User>) {
        Log.d("Debugging", "SearchActivity - onUsersLoaded(): users = ${users.size}")
        adapter.updateUsers(users)
    }

    override fun onUsersFailed(throwable: Throwable) {
        Log.e("Debugging", "SearchActivity - onUsersFailed(): e = ${throwable.stackTrace}")
        Toast.makeText(this, R.string.search_fail, Toast.LENGTH_LONG).show()
    }

    override fun onConversationCreated(conversation: Conversation) {
        val messagesIntent = Intent(this, MessagesActivity::class.java)
        //messagesIntent.putExtra(MessagesActivity.CONVERSATION_EXTRA, conversation)
        startActivity(messagesIntent)
    }

    override fun onConversationFailed(throwable: Throwable) {
        Log.e("Debugging", "SearchActivity - onConversationFailed(): e = ${throwable.message}")
        Toast.makeText(this, R.string.conversation_create_fail, Toast.LENGTH_LONG).show()
    }
}
