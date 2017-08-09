package com.example.marosu.secretchat.messages;

import com.example.marosu.secretchat.R;
import com.example.marosu.secretchat.base.BaseActivity;

public class MessagesActivity extends BaseActivity<MessagesView, MessagesPresenter> implements MessagesView {

    @Override
    protected MessagesPresenter initPresenter() {
        return new MessagesPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_chat_details;
    }
}
