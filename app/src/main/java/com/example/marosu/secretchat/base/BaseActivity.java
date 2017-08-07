package com.example.marosu.secretchat.base;

import android.arch.lifecycle.LifecycleActivity;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;

import butterknife.ButterKnife;

/**
 * Created by Marius-Andrei Rosu on 8/7/2017.
 */
public abstract class BaseActivity<V extends BaseContract.View, P extends BaseContract.Presenter<V>>
        extends LifecycleActivity implements BaseContract.View {
    protected P presenter;

    protected abstract P initPresenter();

    protected abstract int getLayoutId();

    @CallSuper
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);

        BaseViewModel<V, P> viewModel = ViewModelProviders.of(this).get(BaseViewModel.class);
        if (viewModel.getPresenter() == null) {
            viewModel.setPresenter(initPresenter());
        }
        presenter = viewModel.getPresenter();
        presenter.attachLifecycle(getLifecycle());
        presenter.attachView((V) this);
    }

    @CallSuper
    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachLifecycle(getLifecycle());
        presenter.detachView();
    }
}
