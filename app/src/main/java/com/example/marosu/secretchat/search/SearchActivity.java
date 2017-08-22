package com.example.marosu.secretchat.search;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.marosu.secretchat.R;
import com.example.marosu.secretchat.base.BaseActivity;
import com.example.marosu.secretchat.model.entity.User;

import java.util.List;

import butterknife.BindView;
import butterknife.OnTextChanged;

public class SearchActivity extends BaseActivity<SearchView, SearchPresenter> implements SearchView {
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

    @OnTextChanged(R.id.search_input)
    void onSearchTextChanged(CharSequence input) {
        if (input.length() > 2) {
            presenter.searchUsers(input);
        }
    }

    @Override
    public void onUsersLoaded(List<User> users) {
        Log.d("Debugging", "SearchActivity - onUsersLoaded(): users = " + users.size());
        if (adapter == null) {
            adapter = new SearchAdapter(users);
            searchList.setAdapter(adapter);
        } else {
            adapter.updateUsers(users);
        }
    }

    @Override
    public void onUsersFailed(Throwable throwable) {
        Log.e("Debugging", "SearchActivity - onUsersFailed(): e = " + throwable.getStackTrace());
    }
}
