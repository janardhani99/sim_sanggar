package com.example.admin.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import com.example.admin.GlobalClass
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
import kotlinx.android.synthetic.main.activity_detail_user.*

class DetailUserActivity : BaseActivity(), UserContract.View, SanggarContract.View {

    var data_user: UserData? = null
    var data_sanggar : List<SanggarData>? = null

    var presenter = UserPresenter(this)
    val presenterSanggar = SanggarPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_user)

        setToolbar()
        data_user = intent.getParcelableExtra("data_user")

        data_user?.let { setView(it) }
        presenterSanggar.getSanggar()

        initListener()
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
        val selectedSanggar = data_sanggar?.find{it.nama == ac_nama_sanggar?.text.toString()}?.id
        val password = til_password_user?.editText?.text.toString()

        val editData = HashMap<String, Any?>()
        editData["username"] = username
        editData["telepon"] = telepon
        editData["email"] = email
        editData["sanggar_id"] = selectedSanggar
        editData["password"] = password

        isLoading(true)

        if (data_user == null) {
            presenter.addUser(editData)
        } else {
            data_user?.id?.let { presenter.editUser(it, editData) }
        }


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

    override fun getProfileResponse(response: UserResponse) {
        TODO("Not yet implemented")
    }

    override fun editProfileResponse(response: UserResponse) {
        TODO("Not yet implemented")
    }

    override fun getSanggarResponse(response: SanggarListResponse) {
        data_sanggar = response.data
        initAdapter()
    }

    override fun sanggarResponse(response: SanggarResponse) {
        isLoading(false)
        this.showCustomDialogBack("Berhasil", "Data Tersimpan")
    }

    override fun showError(title: String, message: String) {
       this.showError(title, message)
    }
}