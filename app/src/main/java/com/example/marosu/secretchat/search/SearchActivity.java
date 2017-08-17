package com.example.marosu.secretchat.search;

import android.os.Bundle;

import com.example.marosu.secretchat.R;
import com.example.marosu.secretchat.base.BaseActivity;

public class SearchActivity extends BaseActivity<SearchView, SearchPresenter> {

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
    }
}
