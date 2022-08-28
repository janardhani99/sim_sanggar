package com.example.admin.view.activity

import android.content.Intent
import android.os.Bundle
import com.example.admin.GlobalClass
import com.example.admin.R
import com.example.admin.common.Preferences
import com.example.admin.common.Utilities
import com.example.admin.common.clickWithDebounce
import com.example.admin.common.loadImage
import com.example.admin.data.model.common.EmptyResponse
import com.example.admin.data.model.user.UserData
import com.example.admin.data.model.user.UserListResponse
import com.example.admin.data.model.user.UserResponse
import com.example.admin.presenter.user.UserContract
import com.example.admin.presenter.user.UserPresenter
import com.example.admin.view.activity.auth.LoginActivity
import com.example.admin.view.activity.common.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity :  BaseActivity(), UserContract.View {
    var preferences = Preferences(GlobalClass.context)
    var data : UserData? = null
    var presenter = UserPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter.getProfile()

        btn_edit_profil?.clickWithDebounce {
            val intent = Intent(this, EditProfilActivity::class.java)
            startActivity(intent)
        }

        cv_kelola_user?.clickWithDebounce {
            val intent = Intent(this, KelolaUserActivity::class.java)
            startActivity(intent)
        }

        cv_kelola_user_sanggar?.clickWithDebounce {
            val intent = Intent(this, KelolaUserSanggarActivity::class.java)
            startActivity(intent)
        }

    }

    private fun initView(data: UserData) {
        data.run{
            tv_username?.setText(data.username)
            data.photoUrl?.let { iv_profile_pict?.loadImage(it) }
        }
    }

    fun fetchData() {
        isLoading(true)
        presenter.getProfile()
    }

    override fun onResume() {
        super.onResume()
        fetchData()
    }

    private fun isLoading(isLoad: Boolean) {
        if (isLoad) { Utilities.showProgress(this) }
        else Utilities.hideProgress()
    }

    override fun userResponse(response: UserResponse) {
        TODO("Not yet implemented")
    }

    override fun getUserResponse(response: UserListResponse) {
        TODO("Not yet implemented")
    }

    override fun deleteUserResponse(response: EmptyResponse) {
        TODO("Not yet implemented")
    }

    override fun getProfileResponse(response: UserResponse) {
        isLoading(false)
        data = response.data
        data?.let { initView(it) }
    }

    override fun editProfileResponse(response: UserResponse) {
        TODO("Not yet implemented")
    }

    override fun showError(title: String, message: String) {
        this.showErrorAlert(title, message)
    }

}