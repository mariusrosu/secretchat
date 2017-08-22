package com.example.marosu.secretchat.auth.register;

import com.example.marosu.secretchat.R;
import com.example.marosu.secretchat.base.BaseActivity;

public final class RegisterActivity extends BaseActivity<RegisterView, RegisterPresenter> {

    @Override
    protected RegisterPresenter initPresenter() {
        return new RegisterPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }
}
