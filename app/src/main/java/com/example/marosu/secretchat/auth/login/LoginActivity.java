package com.example.marosu.secretchat.auth.login;

import android.content.Intent;
import android.widget.EditText;

import com.example.marosu.secretchat.R;
import com.example.marosu.secretchat.base.BaseActivity;
import com.example.marosu.secretchat.chatList.ChatListActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity<LoginView, LoginPresenter> implements LoginView {
    @BindView(R.id.login_username)
    EditText usernameText;

    @BindView(R.id.login_password)
    EditText passwordText;

    @Override
    protected LoginPresenter initPresenter() {
        return new LoginPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void onLoginSuccess() {
        final Intent chatListIntent = new Intent(this, ChatListActivity.class);
        startActivity(chatListIntent);
    }

    @Override
    public void onLoginFail() {

    }

    @OnClick(R.id.login_button)
    protected void onLoginClick() {
        final String username = usernameText.getText().toString();
        final String password = passwordText.getText().toString();
        presenter.login(username, password);
    }
}
