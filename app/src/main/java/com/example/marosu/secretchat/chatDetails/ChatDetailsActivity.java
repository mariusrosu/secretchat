package com.example.marosu.secretchat.chatDetails;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.marosu.secretchat.R;
import com.example.marosu.secretchat.base.BaseActivity;

public class ChatDetailsActivity extends BaseActivity<ChatDetailsView, ChatDetailsPresenter> implements ChatDetailsView {

    @Override
    protected ChatDetailsPresenter initPresenter() {
        return new ChatDetailsPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_chat_details;
    }
}
