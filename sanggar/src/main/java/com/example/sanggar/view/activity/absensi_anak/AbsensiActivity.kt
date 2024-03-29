package com.example.sanggar.view.activity.absensi_anak

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sanggar.R
import com.example.sanggar.common.Utilities
import com.example.sanggar.common.clickWithDebounce
import com.example.sanggar.data.model.common.EmptyResponse
import com.example.sanggar.data.model.jadwal_sanggar.JadwalSanggarItem
import com.example.sanggar.data.model.jadwal_sanggar.JadwalSanggarListResponse
import com.example.sanggar.data.model.jadwal_sanggar.JadwalSanggarResponse
import com.example.sanggar.presenter.jadwal_sanggar.JadwalSanggarContract
import com.example.sanggar.presenter.jadwal_sanggar.JadwalSanggarPresenter
import com.example.sanggar.view.activity.common.BaseActivity
import com.example.sanggar.view.activity.common.ButtonDialogListener
import com.example.sanggar.view.activity.jadwal_sanggar.JadwalSanggarActivity
import com.example.sanggar.view.adapter.absensi.AbsensiAdapter
import com.example.sanggar.view.adapter.jadwal_sanggar.JadwalSanggarAdapter
import com.example.sanggar.view.fragment.jadwal_sanggar.JadwalSanggarBottomSheetFragment
import kotlinx.android.synthetic.main.activity_absensi.*
import kotlinx.android.synthetic.main.activity_absensi.rv_kelas
import kotlinx.android.synthetic.main.activity_absensi.sr_kelas_recycler
import kotlinx.android.synthetic.main.activity_jadwal_sanggar.*
import kotlinx.android.synthetic.main.fragment_absensi.*
import kotlinx.android.synthetic.main.fragment_toolbar.*

class AbsensiActivity : BaseActivity(), JadwalSanggarContract.View {
//    var data: JadwalSanggarItem? = null
    private var presenter = JadwalSanggarPresenter(this)
    lateinit var adapter: AbsensiAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_absensi)
        //init toolbar
        setToolbar()


        initListener()
        initAdapter()
        fetchData()
    }

    private fun initAdapter() {
        adapter = AbsensiAdapter{ itemEdit->
            val intent = Intent(this, PertemuanActivity::class.java)
            intent.putExtra("data_kelas", itemEdit)
            startActivity(intent)
        }
        rv_kelas?.layoutManager = LinearLayoutManager(this)
        rv_kelas?.adapter = adapter
    }

    private fun initListener() {

        sr_kelas_recycler?.setOnRefreshListener {
            fetchData()
        }

        tambah_kelas?.clickWithDebounce {
            startActivity(Intent(this, JadwalSanggarActivity::class.java))
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
            sr_kelas_recycler?.isRefreshing = false
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