package com.example.sanggar.view.activity.auth

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.sanggar.GlobalClass
import com.example.sanggar.MainTabActivity
import com.example.sanggar.R
import com.example.sanggar.common.Preferences
import com.example.sanggar.common.Utilities
import com.example.sanggar.common.clickWithDebounce
import com.example.sanggar.data.model.auth.AuthResponse
import com.example.sanggar.presenter.auth.AuthContract
import com.example.sanggar.presenter.auth.AuthPresenter
import com.example.sanggar.view.activity.common.BaseActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity(), AuthContract.View {
    val presenter =  AuthPresenter(this)
    val preferences = Preferences(GlobalClass.context)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        checkLogin()
        initListener()
    }

    private fun checkLogin() {
        if(preferences.userLoggedIn) {
            finishAffinity()
            startActivity(Intent(this, MainTabActivity::class.java))
        }
    }

    private fun initListener() {
        btn_login?.clickWithDebounce {
            loginProcess()

        }
        btn_register_login?.clickWithDebounce {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }

    private fun loginProcess() {
        val email = til_email_login?.editText?.text.toString()
        val password = til_password_login?.editText?.text.toString()

        val loginData = HashMap<String, Any?>()
        loginData["email"] = email
        loginData["password"] = password
        isLoadingProcess(true)
        presenter.login(loginData)
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