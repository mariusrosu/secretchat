package com.example.marosu.secretchat.auth.login;

import android.os.AsyncTask;

import com.example.marosu.secretchat.base.BasePresenter;
import com.example.marosu.secretchat.util.Util;

import java.lang.ref.WeakReference;

/**
 * Created by Marius-Andrei Rosu on 8/7/2017.
 */
public final class LoginPresenter extends BasePresenter<LoginView> {

    public void login(String username, String password) {
        if (!Util.isEmailValid(username)) {
            getView().onEmailInvalid();
            return;
        }
        if (!Util.isPasswordValid(password)) {
            getView().onPasswordInvalid();
            return;
        }
        getView().showLoading();
        //TODO: Implement login!
        new FakeLoginTask(getView()).execute();
    }

    private static class FakeLoginTask extends AsyncTask<Void, Void, Boolean> {
        private final WeakReference<LoginView> viewWeakReference;

        public FakeLoginTask(LoginView view) {
            this.viewWeakReference = new WeakReference<>(view);
        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            try {
                Thread.sleep(3000);
                return true;
            } catch (InterruptedException e) {
                return false;
            }
        }

        @Override
        protected void onPostExecute(Boolean result) {
            final LoginView view = viewWeakReference.get();
            if (view != null) {
                if (result) {
                    view.onLoginSuccess();
                } else {
                    view.onLoginFail();
                }
            }
        }
    }
}
