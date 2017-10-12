package com.example.marosu.secretchat.auth.register

import android.content.SharedPreferences
import android.util.Log
import com.example.marosu.secretchat.auth.isEmailValid
import com.example.marosu.secretchat.auth.isNameValid
import com.example.marosu.secretchat.auth.isPasswordValid
import com.example.marosu.secretchat.base.BasePresenter
import com.example.marosu.secretchat.model.api.SecretChatClient
import com.example.marosu.secretchat.model.body.LoginBody
import com.example.marosu.secretchat.model.body.RegisterBody
import retrofit2.Response

/**
 * Created by Marius-Andrei Rosu on 10/10/2017.
 */
class RegisterPresenter(private val prefs: SharedPreferences) : BasePresenter<RegisterView>() {
    private val api = SecretChatClient.createApi()

    private companion object {
        const val AUTHORIZATION = "Authorization"
    }

    fun register(firstName: CharSequence, lastName: CharSequence, email: CharSequence,
                 password: CharSequence) {
        if (verifyCredentials(firstName, lastName, email, password)) {
            val body = RegisterBody(firstName.toString(), lastName.toString(), email.toString(), password.toString())
            disposables.add(api.register(body)
                    .compose(applySchedulers())
                    .subscribe(
                            { onNextRegister(email.toString(), password.toString()) },
                            { error -> handleRegistrationError(error) }))
        }
    }

    private val onNextRegister = { email: String, password: String ->
        disposables.add(api.login(LoginBody(email, password))
                .compose(applySchedulers())
                .subscribe(
                        { response -> onNextLogin(response) },
                        { error -> handleRegistrationError(error) }))
    }

    private val onNextLogin = { response: Response<Void> ->
        val authorization = response.headers().get(AUTHORIZATION)
        val editor = prefs.edit()
        editor.putString(AUTHORIZATION, authorization)
        editor.apply()
    }

    private fun handleRegistrationError(error: Throwable) {
        Log.d("Debugging", "handleRegistrationError(): $error")
        view.onRegistrationError()
    }

    private fun verifyCredentials(firstName: CharSequence, lastName: CharSequence,
                                  email: CharSequence, password: CharSequence): Boolean {
        if (!firstName.isNameValid()) {
            view.onFirstNameInvalid()
            return false
        }

        if (!lastName.isNameValid()) {
            view.onLastNameInvalid()
            return false
        }

        if (!email.isEmailValid()) {
            view.onEmailInvalid()
            return false
        }

        if (!password.isPasswordValid()) {
            view.onPasswordInvalid()
            return false
        }

        return true
    }
}
