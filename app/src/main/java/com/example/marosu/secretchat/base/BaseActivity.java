package com.example.marosu.secretchat.base;

import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.LifecycleRegistryOwner;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

/**
 * Created by Marius-Andrei Rosu on 8/7/2017.
 */
public abstract class BaseActivity<V extends BaseContract.View, P extends BaseContract.Presenter<V>>
        extends AppCompatActivity implements LifecycleRegistryOwner, BaseContract.View {
    protected P presenter;
    private final LifecycleRegistry mRegistry = new LifecycleRegistry(this);

    protected abstract P initPresenter();

    protected abstract int getLayoutId();

    @Override
    public LifecycleRegistry getLifecycle() {
        return mRegistry;
    }

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
