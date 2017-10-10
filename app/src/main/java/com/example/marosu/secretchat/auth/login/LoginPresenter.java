package com.example.marosu.secretchat.auth.login;

import com.example.marosu.secretchat.base.BasePresenter;
import com.example.marosu.secretchat.model.api.SecretChatApi;
import com.example.marosu.secretchat.model.api.SecretChatClient;
import com.example.marosu.secretchat.util.Util;

import org.whispersystems.libsignal.logging.Log;

import io.reactivex.Observable;

final class LoginPresenter extends BasePresenter<LoginView> {
    private SecretChatApi api;

    public LoginPresenter() {
        this.api = SecretChatClient.createApi();
    }

    public void login(String username, String password) {
        if (!Util.isEmailValid(username)) {
            getView().onEmailInvalid();
            return;
        }
        //TODO: Add password check
        /*if (!Util.isPasswordValid(password)) {
            getView().onPasswordInvalid();
            return;
        }*/

        //TODO: Replace simulation with api.login(new LoginBody(username, password))
        disposables.add(simulateLogin()
                .compose(applySchedulers())
                .doOnSubscribe(disposable -> getView().showLoading())
                .doOnError(this::handleLoginFail)
                .doOnNext(this::handleLoginSuccess)
                .subscribe());
    }

    private void handleLoginSuccess(Object response) {
        Log.d("Debugging", "onSuccess(): response = " + response);
        getView().onLoginSuccess();
    }

    private void handleLoginFail(Throwable throwable) {
        Log.e("Debugging", "onError(): t = " + throwable);
        getView().onLoginFail();
    }

    private Observable<Object> simulateLogin() {
        return Observable.fromCallable(() -> {
            try {
                Thread.sleep(2000);
                return true;
            } catch (InterruptedException e) {
                return false;
            }
        });
    }
}
