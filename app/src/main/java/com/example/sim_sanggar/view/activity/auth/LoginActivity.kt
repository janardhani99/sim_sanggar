package com.example.sim_sanggar.view.activity.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat.finishAffinity
import androidx.core.content.ContextCompat.startActivity
import com.example.sim_sanggar.GlobalClass
import com.example.sim_sanggar.MainTabActivity
import com.example.sim_sanggar.R
import com.example.sim_sanggar.common.Preferences
import com.example.sim_sanggar.common.Utilities
import com.example.sim_sanggar.common.clickWithDebounce
import com.example.sim_sanggar.data.model.auth.AuthResponse
import com.example.sim_sanggar.presenter.auth.AuthContract
import com.example.sim_sanggar.presenter.auth.AuthPresenter
import com.example.sim_sanggar.view.activity.common.BaseActivity
import com.example.sim_sanggar.view.fragment.beranda.BerandaFragment
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
        val password = til_password_user_login?.editText?.text.toString()

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
            accessToken = data?.accesToken
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