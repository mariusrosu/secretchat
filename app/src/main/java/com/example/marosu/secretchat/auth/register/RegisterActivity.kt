package com.example.marosu.secretchat.auth.register

import android.os.Bundle
import android.util.Log
import com.example.marosu.secretchat.R
import com.example.marosu.secretchat.base.BaseActivity
import com.example.marosu.secretchat.util.Util.PREFS_NAME
import kotlinx.android.synthetic.main.activity_register.*

/**
 * Created by Marius-Andrei Rosu on 10/10/2017.
 */
class RegisterActivity : BaseActivity<RegisterView, RegisterPresenter>(), RegisterView {
    override fun initPresenter() = RegisterPresenter(getSharedPreferences(PREFS_NAME, MODE_PRIVATE))

    override fun getLayoutId() = R.layout.activity_register

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        registerButton.setOnClickListener {
            presenter.register(registerFirstName.text, registerLastName.text, registerEmail.text,
                    registerPassword.text)
        }
    }

    override fun onFirstNameInvalid() {
        Log.d("Debugging", "onFirstNameInvalid()")
    }

    override fun onLastNameInvalid() {
        Log.d("Debugging", "onLastNameInvalid()")
    }

    override fun onEmailInvalid() {
        Log.d("Debugging", "onEmailInvalid()")
    }

    override fun onPasswordInvalid() {
        Log.d("Debugging", "onPasswordInvalid()")
    }

    override fun onRegistrationError() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onRegistrationConflict() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
