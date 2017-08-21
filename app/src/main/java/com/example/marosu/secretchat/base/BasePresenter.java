package com.example.marosu.secretchat.base;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by Marius-Andrei Rosu on 8/7/2017.
 */
public abstract class BasePresenter<V extends BaseContract.View> implements BaseContract.Presenter<V>, LifecycleObserver {
    private V view;
    protected CompositeDisposable disposables = new CompositeDisposable();

    @Override
    final public void attachView(V view) {
        this.view = view;
    }

    @Override
    final public void detachView() {
        view = null;
        disposables.dispose();
    }

    @Override
    final public boolean isViewAttached() {
        return view != null;
    }

    @Override
    final public V getView() {
        return view;
    }

    @Override
    final public void attachLifecycle(Lifecycle lifecycle) {
        lifecycle.addObserver(this);
    }

    @Override
    final public void detachLifecycle(Lifecycle lifecycle) {
        lifecycle.removeObserver(this);
    }

    @Override
    public void onPresenterDestroy() {
        //Implement this to clean up the presenter
    }
}
