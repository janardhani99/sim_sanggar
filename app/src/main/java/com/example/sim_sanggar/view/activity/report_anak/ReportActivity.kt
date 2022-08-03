package com.example.sim_sanggar.view.activity.report_anak

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sim_sanggar.R
import com.example.sim_sanggar.common.Utilities
import com.example.sim_sanggar.data.model.common.EmptyResponse
import com.example.sim_sanggar.data.model.jadwal_sanggar.JadwalSanggarListResponse
import com.example.sim_sanggar.data.model.jadwal_sanggar.JadwalSanggarResponse
import com.example.sim_sanggar.presenter.jadwal_sanggar.JadwalSanggarContract
import com.example.sim_sanggar.presenter.jadwal_sanggar.JadwalSanggarPresenter
import com.example.sim_sanggar.view.activity.common.BaseActivity
import com.example.sim_sanggar.view.adapter.report_anak.ReportAdapter
import kotlinx.android.synthetic.main.activity_report.*
import kotlinx.android.synthetic.main.fragment_toolbar.*

class ReportActivity : BaseActivity(), JadwalSanggarContract.View {
//    var data: JadwalSanggarItem? = null
    private var presenter = JadwalSanggarPresenter(this)
    lateinit var adapter: ReportAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_report)
        //init toolbar
        setToolbar()

        initListener()
        initAdapter()
        fetchData()
    }

    private fun initAdapter() {
        adapter = ReportAdapter{ itemEdit->
            val intent = Intent(this, ReportAnakActivity::class.java)
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