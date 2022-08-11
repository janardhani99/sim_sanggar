package com.example.sim_sanggar.view.activity.auth

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import com.example.sim_sanggar.GlobalClass
import com.example.sim_sanggar.MainTabActivity
import com.example.sim_sanggar.R
import com.example.sim_sanggar.common.Preferences
import com.example.sim_sanggar.common.Utilities
import com.example.sim_sanggar.common.clickWithDebounce
import com.example.sim_sanggar.data.model.auth.AuthResponse
import com.example.sim_sanggar.data.model.sanggar.SanggarData
import com.example.sim_sanggar.data.model.sanggar.SanggarListResponse
import com.example.sim_sanggar.presenter.auth.AuthContract
import com.example.sim_sanggar.presenter.auth.AuthPresenter
import com.example.sim_sanggar.presenter.sanggar.SanggarContract
import com.example.sim_sanggar.presenter.sanggar.SanggarPresenter
import com.example.sim_sanggar.view.activity.common.BaseActivity
import kotlinx.android.synthetic.main.activity_form_daftar_kelas.*

import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity: BaseActivity(), AuthContract.View, SanggarContract.View {

    val presenter = AuthPresenter(this)
    val preferences = Preferences(GlobalClass.context)
//    val namaSanggar : MutableList<SanggarData>? = null
    var data_sanggar : List<SanggarData>? = null
    val presenterSanggar = SanggarPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        presenterSanggar.getSanggar()
        initListener()
        initAdapter()

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

    fun AutoCompleteTextView.setArrayAdapter(list: List<String?>) {
        val adapter = ArrayAdapter(GlobalClass.context, R.layout.layout_dropdown_item, list)
        this.setAdapter(adapter)
    }

    private fun initAdapter() {
        val nama_sanggar = data_sanggar?.map { it.nama}

        ac_nama_sanggar?.run {
            if (nama_sanggar != null) {
                setArrayAdapter(nama_sanggar)
            }


        }

    }


    private fun registerProcess() {
        val email = til_email_user_register?.editText?.text.toString()
        val password = til_password_user_register?.editText?.text.toString()
        val selectedSanggar = data_sanggar?.find{it.nama == ac_nama_sanggar?.text.toString()}
        val username = til_username_user_register?.editText?.text.toString()
        val nomor_telepon = til_telepon_user_register?.editText?.text.toString()

        val registerData = HashMap<String, Any?>()
        registerData["email"] = email
        registerData["password"] = password
        registerData["sanggar_id"] = selectedSanggar
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

    override fun getSanggarResponse(response: SanggarListResponse) {
        data_sanggar = response.data
        initAdapter()
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