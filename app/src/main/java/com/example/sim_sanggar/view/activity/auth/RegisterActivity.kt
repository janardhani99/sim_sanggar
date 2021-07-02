package com.example.sim_sanggar.view.activity.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
import kotlinx.android.synthetic.main.activity_login.*

import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity: BaseActivity(), AuthContract.View {

    val presenter = AuthPresenter(this)
    val preferences = Preferences(GlobalClass.context)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        initListener()
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
        val nama_sanggar = til_sanggar_user_register?.editText?.text.toString()
        val username = til_username_user_register?.editText?.text.toString()
        val nomor_telepon = til_telepon_user_register?.editText?.text.toString()

        val registerData = HashMap<String, Any?>()
        registerData["email"] = email
        registerData["password"] = password
        registerData["sanggar_id"] = nama_sanggar
        registerData["username"] = username
        registerData["telepon"] = nomor_telepon
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