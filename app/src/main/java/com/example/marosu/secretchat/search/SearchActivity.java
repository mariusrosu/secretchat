package com.example.marosu.secretchat.search;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.marosu.secretchat.R;
import com.example.marosu.secretchat.base.BaseActivity;
import com.example.marosu.secretchat.messages.MessagesActivity;
import com.example.marosu.secretchat.model.entity.Conversation;
import com.example.marosu.secretchat.model.entity.User;

import java.util.List;

import butterknife.BindView;
import butterknife.OnTextChanged;

public final class SearchActivity extends BaseActivity<SearchView, SearchPresenter> implements SearchView {
    @BindView(R.id.search_recycler)
    RecyclerView searchList;

    private SearchAdapter adapter;

    @Override
    protected SearchPresenter initPresenter() {
        return new SearchPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_search;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        searchList.setLayoutManager(new LinearLayoutManager(this));
        searchList.setHasFixedSize(true);
    }

    @Override
    public void onUsersLoaded(List<User> users) {
        Log.d("Debugging", "SearchActivity - onUsersLoaded(): users = " + users.size());
        if (adapter == null) {
            initSearchAdapter(users);
            searchList.setAdapter(adapter);
        } else {
            adapter.updateUsers(users);
        }
    }

    @Override
    public void onUsersFailed(Throwable throwable) {
        Log.e("Debugging", "SearchActivity - onUsersFailed(): e = " + throwable.getStackTrace());
        Toast.makeText(this, R.string.search_fail, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onConversationCreated(Conversation conversation) {
        final Intent messagesIntent = new Intent(this, MessagesActivity.class);
        messagesIntent.putExtra(MessagesActivity.CONVERSATION_EXTRA, conversation);
        startActivity(messagesIntent);
    }

    @Override
    public void onConversationFailed(Throwable throwable) {
        Log.e("Debugging", "SearchActivity - onConversationFailed(): e = " + throwable.getMessage());
        Toast.makeText(this, R.string.conversation_create_fail, Toast.LENGTH_LONG).show();
    }

    private void initSearchAdapter(List<User> users) {
        adapter = new SearchAdapter(users);
        adapter.getClickSubject().subscribe(user -> presenter.createConversation(user));
    }

    @OnTextChanged(R.id.search_input)
    void onSearchTextChanged(CharSequence input) {
        if (input.length() > 2) {
            presenter.searchUsers(input);
        }
    }
}
