package com.example.marosu.secretchat.auth.login;

import com.example.marosu.secretchat.base.BaseContract;

/**
 * Created by Marius-Andrei Rosu on 8/7/2017.
 */
public interface LoginView extends BaseContract.View {
    void onEmailInvalid();

    void onPasswordInvalid();

    void onLoginSuccess();

    void onLoginFail();
}
