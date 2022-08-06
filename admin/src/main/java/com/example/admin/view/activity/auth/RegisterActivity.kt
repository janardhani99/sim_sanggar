package com.example.admin.view.activity.auth

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import com.example.admin.GlobalClass
import com.example.admin.MainTabActivity
import com.example.admin.R
import com.example.admin.common.Preferences
import com.example.admin.common.Utilities
import com.example.admin.common.clickWithDebounce
import com.example.admin.data.model.auth.AuthResponse
import com.example.admin.presenter.auth.AuthContract
import com.example.admin.presenter.auth.AuthPresenter
import com.example.admin.view.activity.common.BaseActivity

import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity: BaseActivity(), AuthContract.View {

    val presenter = AuthPresenter(this)
    val preferences = Preferences(GlobalClass.context)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        initListener()
//        initAdapter()
    }

    private fun initListener() {
        btn_register?.clickWithDebounce {
            registerProcess()

        }
        btn_login_register?.clickWithDebounce {
            startActivity(Intent(this, LoginActivity::class.java))
//            onBackPressed()
//            startActivity(Intent(this, LoginActivity::class.java))
        }
    }

    private fun registerProcess() {
        val email = til_email_user_register?.editText?.text.toString()
        val password = til_password_user_register?.editText?.text.toString()
        val username = til_username_user_register?.editText?.text.toString()

        val registerData = HashMap<String, Any?>()
        registerData["email"] = email
        registerData["password"] = password
        registerData["username"] = username
        isLoadingProcess(true)
        presenter.register(registerData)
    }

    override fun loginResponse(response: AuthResponse) {
        isLoadingProcess(false)
        val data = response.data
        preferences.apply {
            accessToken = data?.accessToken
            userLoggedIn = true
        }
        finishAffinity()
        startActivity(Intent(this, MainTabActivity::class.java))
    }

    override fun showError(title: String, message: String) {
        isLoadingProcess(false)
        showErrorAlert(title, message)
    }

    fun isLoadingProcess(loading: Boolean) {
        if(loading) Utilities.showProgress(this)
        else Utilities.hideProgress()
    }
}