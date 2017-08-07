package com.example.marosu.secretchat.base;

import android.arch.lifecycle.Lifecycle;

/**
 * Created by Marius-Andrei Rosu on 8/7/2017.
 */
public interface BaseContract {

    interface View {
    }

    interface Presenter<V extends View> {
        void attachLifecycle(Lifecycle lifecycle);

        void detachLifecycle(Lifecycle lifecycle);

        void attachView(V view);

        void detachView();

        V getView();

        boolean isViewAttached();

        void onPresenterDestroy();
    }
}
