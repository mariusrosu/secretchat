package com.example.marosu.secretchat.auth.register

import com.example.marosu.secretchat.base.BaseContract

/**
 * Created by Marius-Andrei Rosu on 10/10/2017.
 */
interface RegisterView : BaseContract.View {
    fun onFirstNameInvalid()

    fun onLastNameInvalid()

    fun onEmailInvalid()

    fun onPasswordInvalid()

    fun onRegistrationError()

    fun onRegistrationConflict()
}