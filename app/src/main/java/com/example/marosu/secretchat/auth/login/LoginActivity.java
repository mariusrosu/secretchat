package com.example.marosu.secretchat.auth.login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.marosu.secretchat.R;
import com.example.marosu.secretchat.auth.register.RegisterActivity;
import com.example.marosu.secretchat.base.BaseActivity;
import com.example.marosu.secretchat.conversations.ConversationsActivity;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnTextChanged;

import static com.example.marosu.secretchat.util.Util.PASSWORD_MIN_LENGTH;

public class LoginActivity extends BaseActivity<LoginView, LoginPresenter> implements LoginView {
    @BindView(R.id.login_logo)
    ImageView loginImage;

    @BindView(R.id.login_username_input_layout)
    TextInputLayout usernameInputLayot;

    @BindView(R.id.login_username)
    EditText usernameText;

    @BindView(R.id.login_password_input_layout)
    TextInputLayout passwordInputLayout;

    @BindView(R.id.login_password)
    EditText passwordText;

    private ProgressDialog loadingDialog;

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
        loadingDialog.dismiss();
        final Intent chatListIntent = new Intent(this, ConversationsActivity.class);
        startActivity(chatListIntent);
        finish();
    }

    @Override
    public void onLoginFail() {
        loadingDialog.dismiss();

    }

    @Override
    public void showLoading() {
        loadingDialog = ProgressDialog.show(this, getString(R.string.please_wait), getString(R.string.login_in), true);
    }

    @OnClick(R.id.login_register_label)
    void onRegisterLabelClick() {
        final Intent registerIntent = new Intent(this, RegisterActivity.class);
        startActivity(registerIntent);
    }

    @OnClick(R.id.login_button)
    void onLoginClick() {
        final String username = usernameText.getText().toString();
        final String password = passwordText.getText().toString();
        presenter.login(username, password);
    }

    @OnTextChanged({R.id.login_username, R.id.login_password})
    void onCredentialsTextChanged() {
        usernameInputLayot.setErrorEnabled(false);
        passwordInputLayout.setErrorEnabled(false);
    }
}
