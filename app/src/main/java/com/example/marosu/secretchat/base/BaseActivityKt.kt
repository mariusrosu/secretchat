package com.example.marosu.secretchat.base

import android.arch.lifecycle.LifecycleRegistry
import android.arch.lifecycle.LifecycleRegistryOwner
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import butterknife.ButterKnife

/**
 * Created by Marius-Andrei Rosu on 9/7/2017.
 */
abstract class BaseActivityKt<V : BaseContract.View, P : BaseContract.Presenter<V>> :
        AppCompatActivity(), BaseContract.View, LifecycleRegistryOwner {

    //TODO: Why leaking this?
    private val lifecycle = LifecycleRegistry(this)
    protected var presenter: P? = null

    protected abstract fun initPresenter(): P

    protected abstract fun getLayoutId(): Int

    override fun getLifecycle() = lifecycle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        ButterKnife.bind(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter?.detachLifecycle(lifecycle)
        presenter?.detachView()
    }
}