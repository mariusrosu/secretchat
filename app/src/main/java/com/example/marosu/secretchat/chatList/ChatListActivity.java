package com.example.marosu.secretchat.chatList;

import com.example.marosu.secretchat.R;
import com.example.marosu.secretchat.base.BaseActivity;

public class ChatListActivity extends BaseActivity<ChatListView, ChatListPresenter> {

    @Override
    protected ChatListPresenter initPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_chat_list;
    }
}
