package com.example.sanggar.view.activity.absensi_anak

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sanggar.R
import com.example.sanggar.common.Utilities
import com.example.sanggar.common.clickWithDebounce
import com.example.sanggar.data.model.absensi.PertemuanData
import com.example.sanggar.data.model.absensi.PertemuanDataListResponse
import com.example.sanggar.data.model.absensi.PertemuanDataResponse
import com.example.sanggar.data.model.common.EmptyResponse
import com.example.sanggar.data.model.jadwal_sanggar.JadwalSanggarItem
import com.example.sanggar.presenter.absensi.PertemuanContract
import com.example.sanggar.presenter.absensi.PertemuanPresenter
import com.example.sanggar.view.activity.common.BaseActivity
import com.example.sanggar.view.adapter.absensi.PertemuanAdapter
import kotlinx.android.synthetic.main.activity_detail_pertemuan.*
import kotlinx.android.synthetic.main.activity_pertemuan.*
import kotlinx.android.synthetic.main.fragment_toolbar.*

class PertemuanActivity : BaseActivity(), PertemuanContract.View {

    var data: PertemuanData? = null
    var data_kelas : JadwalSanggarItem? = null
    private var presenter = PertemuanPresenter(this)
    lateinit var adapter: PertemuanAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pertemuan)

        setToolbar()
        toolbar_title?.text = "Pertemuan"
        data_kelas = intent.getParcelableExtra<JadwalSanggarItem>("data_kelas")

        initAdapter()
        initListener()
        data_kelas?.let { setView(it) }

    }

    private fun initAdapter() {
        adapter = PertemuanAdapter { itemEdit->
            val intent = Intent(this, ProgressAnakActivity::class.java)
            intent.putExtra("data_pertemuan", itemEdit)
            intent.putExtra("data_kelas", data_kelas)
            startActivity(intent)
        }

        rv_pertemuan?.layoutManager = LinearLayoutManager(this)
        rv_pertemuan?.adapter = adapter
    }

    private fun initListener() {
        sr_pertemuan_recycler?.setOnRefreshListener {
            fetchData()
        }

        cv_tambah_pertemuan?.clickWithDebounce {
            var intent = Intent(this, DetailPertemuanActivity::class.java)
            intent.putExtra("data_kelas", data_kelas)
            startActivity(intent)
        }
    }
    private fun setView(data: JadwalSanggarItem?) {
//        data?.id?.let { til_kelas.editText?.setText(it) }
        til_anak.editText?.setText(data?.kategori_latihan)
    }

    private fun fetchData() {
        isLoading(true)
        data_kelas?.id?.let { presenter.getPertemuan(it) }
    }

    private fun isLoading(isLoad: Boolean) {
        if (isLoad) Utilities.showProgress(this)
        else Utilities.hideProgress()
    }

    override fun onResume() {
        super.onResume()
        fetchData()
    }
    override fun pertemuanResponse(response: PertemuanDataResponse) {
        TODO("Not yet implemented")
    }

    override fun getPertemuanResponse(response: PertemuanDataListResponse) {
        isLoading(false)
        response.data?.let { adapter.setData(it) }
    }

    override fun deletePertemuanResponse(response: EmptyResponse) {
        TODO("Not yet implemented")
    }

    override fun showError(title: String, message: String) {
        this.showErrorAlert(title, message)
    }
}