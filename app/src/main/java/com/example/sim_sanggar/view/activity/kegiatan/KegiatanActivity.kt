package com.example.sim_sanggar.view.activity.kegiatan

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sim_sanggar.R
import com.example.sim_sanggar.common.Utilities
import com.example.sim_sanggar.common.clickWithDebounce
import com.example.sim_sanggar.data.model.common.EmptyResponse
import com.example.sim_sanggar.data.model.kegiatan.KegiatanListItem
import com.example.sim_sanggar.data.model.kegiatan.KegiatanListResponse
import com.example.sim_sanggar.data.model.kegiatan.KegiatanResponse
import com.example.sim_sanggar.presenter.kegiatan.KegiatanContract
import com.example.sim_sanggar.presenter.kegiatan.KegiatanPresenter
import com.example.sim_sanggar.view.activity.common.BaseActivity
import com.example.sim_sanggar.view.activity.common.ButtonDialogListener
import com.example.sim_sanggar.view.adapter.kegiatan.KegiatanAdapter
import kotlinx.android.synthetic.main.activity_jadwal_sanggar.*
import kotlinx.android.synthetic.main.activity_kegiatan.*
import kotlinx.android.synthetic.main.fragment_toolbar.*

class KegiatanActivity : BaseActivity(), KegiatanContract.View {

    var data: KegiatanListItem? = null
    private var presenter = KegiatanPresenter(this)
    lateinit var adapter: KegiatanAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kegiatan)

        data = intent.getParcelableExtra<KegiatanListItem>("data")

        setToolbar()
//        toolbar_title?.text = getString(R.string.kegiatan)

        initListener()
        initAdapter()
//        fetchData()
    }

    private fun initListener() {
//        cv_tambah_kegiatan?.clickWithDebounce {
//            val intent = Intent(this, DetailKegiatanActivity::class.java)
//            intent.putExtra("intent", 0)
//            startActivity(intent)
//        }

        sr_kegiatan_sanggar.setOnRefreshListener {
            fetchData()
        }

    }

    private fun initAdapter() {
        adapter = KegiatanAdapter()
        rv_kegiatan?.layoutManager = LinearLayoutManager(this)
        rv_kegiatan?.adapter = adapter
    }

    fun fetchData() {
        isLoading(true)
        presenter.getKegiatan()
    }

    private fun isLoading(isLoad: Boolean) {
        if (isLoad) Utilities.showProgress(this)
        else {
            Utilities.hideProgress()
            sr_kegiatan_sanggar?.isRefreshing = false
        }
    }

    override fun onResume() {
        super.onResume()
        fetchData()
    }

    override fun kegiatanResponse(response: KegiatanResponse) {
        TODO("Not yet implemented")
    }

    override fun getKegiatanResponse(response: KegiatanListResponse) {
        isLoading(false)
        response.data?.let { adapter.setData(it) }
    }

    override fun deleteKegiatanResponse(response: EmptyResponse) {
        isLoading(false)
        fetchData()

    }

    override fun uploadImageResponse() {

    }

    override fun showError(title: String, message: String) {
        this.showErrorAlert(title, message)
    }

}