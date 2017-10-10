package com.example.marosu.secretchat.auth.login;

import com.example.marosu.secretchat.base.BaseContract;

interface LoginView extends BaseContract.View {
    void onEmailInvalid();

    void onPasswordInvalid();

    void onLoginSuccess();

    void onLoginFail();

    void showLoading();
}
