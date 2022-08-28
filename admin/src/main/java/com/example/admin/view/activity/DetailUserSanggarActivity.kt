package com.example.admin.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.admin.R
import com.example.admin.common.Utilities
import com.example.admin.common.clickWithDebounce
import com.example.admin.data.model.common.EmptyResponse
import com.example.admin.data.model.user.*
import com.example.admin.presenter.user.SanggarContract
import com.example.admin.presenter.user.SanggarPresenter
import com.example.admin.presenter.user.UserContract
import com.example.admin.presenter.user.UserPresenter
import com.example.admin.view.activity.common.BaseActivity
import kotlinx.android.synthetic.main.activity_detail_user_sanggar.*

class DetailUserSanggarActivity : BaseActivity(), UserContract.View, SanggarContract.View {
    var data_user: UserData? = null
    var data_sanggar : SanggarData? = null

    var presenter = UserPresenter(this)
    val presenterSanggar = SanggarPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_user_sanggar)

        setToolbar()
        data_user = intent.getParcelableExtra("data_user")

        data_user?.let { setView(it) }
        initListener()
    }
    private fun setView(data: UserData) {
        data.run {
            til_nama_sanggar.editText?.setText(data.sanggar?.nama)
            til_alamat.editText?.setText(data.sanggar?.alamat)
            til_telepon_sanggar.editText?.setText(data.telepon)
            til_email.editText?.setText((data.email))
        }
    }

    private fun addOrEditUser() {
        val sanggar_id = data_user?.sanggar?.id
        val nama_sanggar = til_nama_sanggar.editText?.text.toString()

        val nama_pengelola = til_nama_pengelola.editText?.text.toString()
        val telepon= til_telepon_sanggar.editText?.text.toString()
        val email = til_email.editText?.text.toString()
        val alamat = til_alamat.editText?.text.toString()

        val editData = HashMap<String, Any?>()
        val editDataSanggar = HashMap<String, Any?>()

        editDataSanggar["nama"] = nama_sanggar
        editDataSanggar["alamat"] = alamat

        editData["username"] = nama_pengelola
        editData["telepon"] = telepon
        editData["email"] = email


        isLoading(true)
        if (data_user == null && data_sanggar == null) {
            presenter.addUser(editData)
            presenterSanggar.addSanggar(editDataSanggar)
        } else {
            data_user?.id?.let { presenter.editUser(it, editData) }
            if (sanggar_id != null) {
                presenterSanggar.editProfilSanggar(sanggar_id, editDataSanggar)
            }
        }


    }

    private fun initListener() {
        btn_simpan?.clickWithDebounce {
            addOrEditUser()
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

    override fun getProfileResponse(response: UserResponse) {
        TODO("Not yet implemented")
    }

    override fun editProfileResponse(response: UserResponse) {
        TODO("Not yet implemented")
    }

    override fun getSanggarResponse(response: SanggarListResponse) {
        TODO("Not yet implemented")
    }

    override fun sanggarResponse(response: SanggarResponse) {
        isLoading(false)
        this.showCustomDialogBack("Berhasil", "Data Tersimpan")
    }

    override fun showError(title: String, message: String) {
        this.showError(title, message)
    }
}