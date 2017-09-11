package com.example.marosu.secretchat.auth.login

import com.example.marosu.secretchat.base.BaseContract

/**
 * Created by Marius-Andrei Rosu on 9/11/2017.
 */
interface LoginView : BaseContract.View {
    fun onEmailInvalid()

    fun onPasswordInvalid()

    fun onLoginSuccess()

    fun onLoginFail()

    fun showLoading()
}
