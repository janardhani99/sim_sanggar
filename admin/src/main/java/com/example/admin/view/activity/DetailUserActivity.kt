package com.example.admin.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.admin.R
import com.example.admin.common.Utilities
import com.example.admin.common.clickWithDebounce
import com.example.admin.data.model.common.EmptyResponse
import com.example.admin.data.model.user.UserData
import com.example.admin.data.model.user.UserListResponse
import com.example.admin.data.model.user.UserResponse
import com.example.admin.presenter.user.UserContract
import com.example.admin.presenter.user.UserPresenter
import com.example.admin.view.activity.common.BaseActivity
import kotlinx.android.synthetic.main.activity_detail_user.*
import kotlinx.android.synthetic.main.layout_dropdown_item.view.*

class DetailUserActivity : BaseActivity(), UserContract.View {

    var data_user: UserData? = null
    var presenter = UserPresenter(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_user)

        setToolbar()
        data_user = intent.getParcelableExtra("data_user")

        data_user?.let { setView(it) }
        initListener()
    }

    private fun setView(data: UserData) {
        data.run {
            til_nama_sanggar.editText?.setText(data.sanggar?.nama)
            til_username.editText?.setText(data.username)
            til_telepon.editText?.setText(data.telepon)
            til_email.editText?.setText((data.email))
        }
    }

    private fun editUser() {
        val username = til_username.editText?.text.toString()
        val telepon= til_telepon.editText?.text.toString()
        val email = til_email.editText?.text.toString()

        val editData = HashMap<String, Any?>()
        editData["username"] = username
        editData["telepon"] = telepon
        editData["email"] = email

        isLoading(true)
        data_user?.id?.let { presenter.editUser(it, editData) }

    }

    private fun initListener() {
        btn_simpan?.clickWithDebounce {
            editUser()
        }
    }
    private fun isLoading(isLoad: Boolean) {
        if(isLoad) Utilities.showProgress(this)
        else Utilities.hideProgress()
    }

    override fun userResponse(response: UserResponse) {
        isLoading(false)
        this.showCustomDialogBack("Berhasil", "Data Tersimpan")
    }

    override fun getUserResponse(response: UserListResponse) {
        TODO("Not yet implemented")
    }

    override fun deleteUserResponse(response: EmptyResponse) {
        TODO("Not yet implemented")
    }

    override fun showError(title: String, message: String) {
       this.showError(title, message)
    }
}