package com.example.marosu.secretchat.auth.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.example.marosu.secretchat.R;
import com.example.marosu.secretchat.base.BaseActivity;
import com.example.marosu.secretchat.conversations.ConversationsActivity;

import butterknife.BindView;
import butterknife.OnClick;

import static com.example.marosu.secretchat.util.Util.PASSWORD_MIN_LENGTH;

public class LoginActivity extends BaseActivity<LoginView, LoginPresenter> implements LoginView {
    @BindView(R.id.login_username_input_layout)
    TextInputLayout usernameInputLayot;

    @BindView(R.id.login_username)
    EditText usernameText;

    @BindView(R.id.login_password_input_layout)
    TextInputLayout passwordInputLayout;

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
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        usernameText.addTextChangedListener(inputTextWatcher);
        passwordText.addTextChangedListener(inputTextWatcher);
    }

    @Override
    public void onEmailInvalid() {
        usernameInputLayot.setErrorEnabled(true);
        usernameInputLayot.setError(getString(R.string.invalid_email));
    }

    @Override
    public void onPasswordInvalid() {
        passwordInputLayout.setErrorEnabled(true);
        passwordInputLayout.setError(getString(R.string.invalid_password, PASSWORD_MIN_LENGTH));
    }

    @Override
    public void onLoginSuccess() {
        final Intent chatListIntent = new Intent(this, ConversationsActivity.class);
        startActivity(chatListIntent);
        finish();
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

    private TextWatcher inputTextWatcher = new TextWatcher() {

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            //Not implemented
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            usernameInputLayot.setErrorEnabled(false);
            passwordInputLayout.setErrorEnabled(false);
        }

        @Override
        public void afterTextChanged(Editable editable) {
            //Not implemented
        }
    };
}
