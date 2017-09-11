package com.example.marosu.secretchat.auth.login

import android.util.Log
import com.example.marosu.secretchat.base.BasePresenter
import com.example.marosu.secretchat.model.api.SecretChatClient
import com.example.marosu.secretchat.util.Util
import io.reactivex.Observable

/**
 * Created by Marius-Andrei Rosu on 9/11/2017.
 */
class LoginPresenter : BasePresenter<LoginView>() {
    private val api = SecretChatClient.createApi()

    fun login(username: String, password: String) {
        if (!Util.isEmailValid(username)) {
            view?.onEmailInvalid()
            return
        }

        //TODO: Add password check
        /*if (!Util.isPasswordValid(password)) {
            view?.onPasswordInvalid()
            return
        }*/

        //TODO: Replace simulation with api.login(new LoginBody(username, password))
        disposables.add(simulateLogin()
                .compose(applySchedulers())
                .doOnSubscribe { view?.showLoading() }
                .doOnNext { view?.onLoginSuccess() }
                .doOnError { view?.onLoginFail() }
                .subscribe())
    }

    private fun simulateLogin(): Observable<Any> {
        return Observable.fromCallable {
            try {
                Thread.sleep(2000)
            } catch (e: InterruptedException) {
                Log.e("Debugging", "simulateLogin() = $e")
            }
        }
    }
}
