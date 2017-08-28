package com.example.marosu.secretchat.base;

import android.arch.lifecycle.ViewModel;

/**
 * Created by Marius-Andrei Rosu on 8/7/2017.
 */
public final class BaseViewModel<V extends BaseContract.View, P extends BaseContract.Presenter<V>> extends ViewModel {
    private P presenter;

    public P getPresenter() {
        return this.presenter;
    }

    void setPresenter(P presenter) {
        if (this.presenter == null) {
            this.presenter = presenter;
        }
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        presenter.onPresenterDestroy();
        presenter = null;
    }
}
