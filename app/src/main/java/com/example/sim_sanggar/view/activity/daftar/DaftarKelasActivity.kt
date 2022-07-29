package com.example.sim_sanggar.view.activity.daftar

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sim_sanggar.R
import com.example.sim_sanggar.common.Utilities
import com.example.sim_sanggar.data.model.common.EmptyResponse
import com.example.sim_sanggar.data.model.jadwal_sanggar.JadwalSanggarItem
import com.example.sim_sanggar.data.model.jadwal_sanggar.JadwalSanggarListResponse
import com.example.sim_sanggar.data.model.jadwal_sanggar.JadwalSanggarResponse
import com.example.sim_sanggar.presenter.jadwal_sanggar.JadwalSanggarContract
import com.example.sim_sanggar.presenter.jadwal_sanggar.JadwalSanggarPresenter
import com.example.sim_sanggar.view.activity.common.BaseActivity
import com.example.sim_sanggar.view.adapter.daftar.DaftarKelasAdapter
import kotlinx.android.synthetic.main.activity_daftar_kelas.*

class DaftarKelasActivity : BaseActivity(), JadwalSanggarContract.View {

    var kelas = JadwalSanggarItem()
    lateinit var kelasAdapter : DaftarKelasAdapter
    private var kelasPresenter = JadwalSanggarPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daftar_kelas)

        setToolbar()

        initAdapter()
    }

    private fun initAdapter() {
        kelasAdapter = DaftarKelasAdapter { detailItem ->
            val intent = Intent(this, FormDaftarKelasActivity::class.java)
            intent.putExtra("data_kelas", detailItem)
            startActivity(intent)
        }

        rv_daftar_kelas?.layoutManager = LinearLayoutManager(this)
        rv_daftar_kelas?.adapter = kelasAdapter
    }

    private fun isLoading(isLoad: Boolean) {
        if (isLoad)  { Utilities.showProgress(this) }
        else {
            Utilities.hideProgress()

        }
    }

    private fun fetchData() {
        isLoading(true)
        kelasPresenter.getJadwal()
    }

    override fun onResume() {
        super.onResume()
        fetchData()
    }

    override fun jadwalSanggarResponse(response: JadwalSanggarResponse) {
        TODO("Not yet implemented")
    }

    override fun getJadwalSanggarResponse(response: JadwalSanggarListResponse) {
        isLoading(false)
        response.data?.let { kelasAdapter.setData(it) }
    }

    override fun deleteJadwalResponse(response: EmptyResponse) {
        TODO("Not yet implemented")
    }


    override fun showError(title: String, message: String) {
        TODO("Not yet implemented")
    }
}