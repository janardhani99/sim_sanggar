package com.example.admin.view.activity

import android.content.DialogInterface
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.admin.R
import com.example.admin.common.Utilities
import com.example.admin.data.model.common.EmptyResponse
import com.example.admin.data.model.user.UserData
import com.example.admin.data.model.user.UserListResponse
import com.example.admin.data.model.user.UserResponse
import com.example.admin.presenter.user.UserContract
import com.example.admin.presenter.user.UserPresenter
import com.example.admin.view.activity.common.BaseActivity
import com.example.admin.view.UserAdapter
import com.example.admin.view.activity.common.ButtonDialogListener
import kotlinx.android.synthetic.main.activity_kelola_user.*

class KelolaUserActivity : BaseActivity(), UserContract.View {

    var data_user : UserData? = null
    lateinit var adapter: UserAdapter
    private var presenter = UserPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kelola_user)

        setToolbar()
        initAdapter()
        initListener()
    }

    private fun initAdapter() {
        adapter = UserAdapter({ editItem ->
//            val intent = Intent(this, AnakActivity::class.java)
//            intent.putExtra("data_anak", detailItem)
//            startActivity(intent)
        }, { deleteItem ->
            showConfirmationDialog("Konfirmasi", "Apakah anda yakin?", object : ButtonDialogListener {
                override fun onOkButton(dialog: DialogInterface) {
                    isLoading(true)
                    deleteItem.id?.let { presenter.deleteUser(it) }
                    dialog.dismiss()
                }
            })

        })
        rv_user?.layoutManager = LinearLayoutManager(this)
        rv_user?.adapter = adapter
    }

    private fun initListener() {
        sr_list_user?.setOnRefreshListener {
            fetchData()
        }
    }

    override fun onResume() {
        super.onResume()
        fetchData()
    }


    fun fetchData() {
        isLoading(true)
        presenter.getUser()
    }

    private fun isLoading(isLoad: Boolean) {
        if(isLoad)  Utilities.showProgress(this)
        else {
            Utilities.hideProgress()
            sr_list_user.isRefreshing = false
        }
    }
    override fun userResponse(response: UserResponse) {
        TODO("Not yet implemented")
    }

    override fun getUserResponse(response: UserListResponse) {
        isLoading(false)
        response.data?.let{ adapter.setData(it)}
    }

    override fun deleteUserResponse(response: EmptyResponse) {
        isLoading(false)
        fetchData()
    }

    override fun showError(title: String, message: String) {
        showError(title, message)
    }
}