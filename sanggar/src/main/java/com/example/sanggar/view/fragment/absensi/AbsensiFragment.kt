package com.example.sanggar.view.fragment.absensi

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sanggar.R
import com.example.sanggar.common.Utilities
import com.example.sanggar.common.clickWithDebounce
import com.example.sanggar.data.model.common.EmptyResponse
import com.example.sanggar.data.model.jadwal_sanggar.JadwalSanggarListResponse
import com.example.sanggar.data.model.jadwal_sanggar.JadwalSanggarResponse
import com.example.sanggar.presenter.jadwal_sanggar.JadwalSanggarContract
import com.example.sanggar.presenter.jadwal_sanggar.JadwalSanggarPresenter
import com.example.sanggar.view.activity.absensi_anak.PertemuanActivity
import com.example.sanggar.view.adapter.absensi.AbsensiAdapter
import kotlinx.android.synthetic.main.activity_absensi.*
import kotlinx.android.synthetic.main.activity_absensi.rv_kelas
import kotlinx.android.synthetic.main.activity_absensi.sr_kelas_recycler
import kotlinx.android.synthetic.main.fragment_absensi.*

class AbsensiFragment : Fragment(), JadwalSanggarContract.View {

    private var presenter = JadwalSanggarPresenter(this)
    lateinit var adapter: AbsensiAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        initListener()
        initAdapter()
        fetchData()
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_absensi, container, false)
    }

    private fun initAdapter() {
        adapter = AbsensiAdapter{ itemEdit->
            val intent = Intent(this.context, PertemuanActivity::class.java)
            intent.putExtra("data_kelas", itemEdit)
            startActivity(intent)
        }
        rv_kelas?.layoutManager = LinearLayoutManager(this.activity)
        rv_kelas?.adapter = adapter
    }

    private fun initListener() {

        sr_kelas_recycler?.setOnRefreshListener {
            fetchData()
        }
        tambah_pertemuan?.clickWithDebounce {
            startActivity(Intent(context, PertemuanActivity::class.java))
        }
    }

    fun fetchData() {
        isLoading(true)
        presenter.getJadwal()
    }

    private fun isLoading(isload: Boolean){
        if (isload) this.context?.let { Utilities.showProgress(it) }
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
        this.showError(title, message)
    }


}