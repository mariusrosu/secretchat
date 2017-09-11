package com.example.marosu.secretchat.auth.login

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import com.example.marosu.secretchat.R
import com.example.marosu.secretchat.auth.register.RegisterActivity
import com.example.marosu.secretchat.base.BaseActivity
import com.example.marosu.secretchat.conversations.ConversationsActivity
import com.example.marosu.secretchat.util.SimpleTextWatcher
import com.example.marosu.secretchat.util.Util.PASSWORD_MIN_LENGTH
import kotlinx.android.synthetic.main.activity_login.*

/**
 * Created by Marius-Andrei Rosu on 9/8/2017.
 */
@Suppress("DEPRECATION")
class LoginActivity : BaseActivity<LoginView, LoginPresenter>(), LoginView {
    private lateinit var loadingDialog: ProgressDialog

    override fun initPresenter() = LoginPresenter()

    override fun getLayoutId() = R.layout.activity_login

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        login_register_label.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        login_button.setOnClickListener {
            val username = login_username.text.toString()
            val password = login_password.text.toString()
            presenter.login(username, password)
        }

        login_username.addTextChangedListener(object : SimpleTextWatcher() {
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                onCredentialsTextChanged()
            }

        })
        login_password.addTextChangedListener(object : SimpleTextWatcher() {
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                onCredentialsTextChanged()
            }

        })
    }

    override fun onEmailInvalid() {
        login_username_input_layout.isErrorEnabled = true
        login_username_input_layout.error = getString(R.string.invalid_email)
    }

    override fun onPasswordInvalid() {
        login_password_input_layout.isErrorEnabled = true
        login_password_input_layout.error = getString(R.string.invalid_password, PASSWORD_MIN_LENGTH)
    }

    override fun onLoginSuccess() {
        loadingDialog.dismiss()
        val chatListIntent = Intent(this, ConversationsActivity::class.java)
        startActivity(chatListIntent)
        finish()
    }

    override fun onLoginFail() {
        loadingDialog.dismiss()
    }

    override fun showLoading() {
        loadingDialog = ProgressDialog.show(this,
                getString(R.string.please_wait), getString(R.string.login_in), true)
    }

    private fun onCredentialsTextChanged() {
        login_username_input_layout.isErrorEnabled = false
        login_password_input_layout.isErrorEnabled = false
    }
}
