package com.example.sanggar.view.activity.jadwal_sanggar

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sanggar.GlobalClass
import com.example.sanggar.R
import com.example.sanggar.common.Preferences
import com.example.sanggar.common.Utilities
import com.example.sanggar.common.clickWithDebounce
import com.example.sanggar.data.model.common.EmptyResponse
import com.example.sanggar.data.model.jadwal_sanggar.JadwalSanggarListResponse
import com.example.sanggar.data.model.jadwal_sanggar.JadwalSanggarResponse
import com.example.sanggar.presenter.jadwal_sanggar.JadwalSanggarContract
import com.example.sanggar.presenter.jadwal_sanggar.JadwalSanggarPresenter
import com.example.sanggar.view.activity.common.BaseActivity
import com.example.sanggar.view.activity.common.ButtonDialogListener
import com.example.sanggar.view.adapter.jadwal_sanggar.JadwalSanggarAdapter
import com.example.sanggar.view.fragment.jadwal_sanggar.JadwalSanggarBottomSheetFragment
import kotlinx.android.synthetic.main.activity_jadwal_sanggar.*
import kotlinx.android.synthetic.main.fragment_toolbar.*

class JadwalSanggarActivity : BaseActivity(), JadwalSanggarContract.View {
    private var presenter = JadwalSanggarPresenter(this)
    lateinit var adapter: JadwalSanggarAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jadwal_sanggar)
        //init toolbar
        setToolbar()
//        toolbar_title?.text = getString(R.string.kelas)

        initListener()
        initAdapter()
        fetchData()
    }

    private fun initAdapter() {
        adapter = JadwalSanggarAdapter({itemEdit->
            val bottomSheet = JadwalSanggarBottomSheetFragment(itemEdit)
            bottomSheet.show(supportFragmentManager,"")
        }, {deleteItem->
            showConfirmationDialog("Konfirmasi", "Apakah anda yakin?", object : ButtonDialogListener {
                override fun onOkButton(dialog: DialogInterface) {
                    isLoading(true)
                    deleteItem.id?.let { presenter.deleteJadwal(it) }
                    dialog.dismiss()
                }
            })
        })
        rv_jadwal_sanggar?.layoutManager = LinearLayoutManager(this)
        rv_jadwal_sanggar?.adapter = adapter
    }

    private fun initListener() {
        cv_tambah_jadwal?.clickWithDebounce {
            val bottomSheet = JadwalSanggarBottomSheetFragment()
            bottomSheet.show(supportFragmentManager, "")
        }
        sr_jadwal_sanggar?.setOnRefreshListener {
            fetchData()
        }
    }

    fun fetchData() {
        isLoading(true)
        presenter.getJadwal()
    }

    private fun isLoading(isload: Boolean){
        if (isload) Utilities.showProgress(this)
        else {
            Utilities.hideProgress()
            sr_jadwal_sanggar?.isRefreshing = false
        }
    }

    override fun jadwalSanggarResponse(response: JadwalSanggarResponse) {

    }

    override fun getJadwalSanggarResponse(response: JadwalSanggarListResponse) {
        isLoading(false)
        response.data?.let { adapter.setData(it) }
    }

    override fun deleteJadwalResponse(response: EmptyResponse) {
        isLoading(false)
        fetchData()
    }

    override fun showError(title: String, message: String) {
        showErrorAlert(title, message)
    }

}