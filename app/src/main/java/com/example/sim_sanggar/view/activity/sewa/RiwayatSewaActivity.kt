package com.example.sim_sanggar.view.activity.sewa

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sim_sanggar.R
import com.example.sim_sanggar.common.Utilities
import com.example.sim_sanggar.data.model.anak.AnakListItem
import com.example.sim_sanggar.data.model.sewa.SewaListItem
import com.example.sim_sanggar.data.model.sewa.SewaListResponse
import com.example.sim_sanggar.data.model.sewa.SewaResponse
import com.example.sim_sanggar.presenter.sewa.SewaContract
import com.example.sim_sanggar.presenter.sewa.SewaPresenter
import com.example.sim_sanggar.view.activity.common.BaseActivity
import com.example.sim_sanggar.view.adapter.sewaadapter.SewaAdapter
import kotlinx.android.synthetic.main.activity_riwayat_sewa.*
import kotlinx.android.synthetic.main.fragment_toolbar.*

class RiwayatSewaActivity :BaseActivity(), SewaContract.View {
//    var data : SewaListItem? = null
    private var presenter = SewaPresenter(this)
    lateinit var adapter: SewaAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_riwayat_sewa)

//        data = intent.getParcelableExtra("data")

        setToolbar()
        toolbar_title?.text = getString(R.string.riwayat_sewa)

        initAdapter()
    }

    private fun initAdapter() {

        adapter = SewaAdapter()
        rv_riwayat_sewa?.layoutManager = LinearLayoutManager(this)
        rv_riwayat_sewa?.adapter = adapter
    }

    private fun fetchData() {
        isLoading(true)
        presenter.getSewa()
    }

    private fun isLoading(isLoad: Boolean) {
        if (isLoad) Utilities.showProgress(this)
        else Utilities.hideProgress()
    }

    override fun onResume() {
        super.onResume()
        fetchData()
    }

    override fun sewaResponse(response: SewaResponse) {
        TODO("Not yet implemented")
    }

    override fun getSewaResponse(response: SewaListResponse) {
        isLoading(false)
        response.data?.let { adapter.setData(it) }
    }

    override fun getTanggalSewaResponse(response: SewaListResponse) {
        TODO("Not yet implemented")
    }

    override fun uploadImageResponse() {
        TODO("Not yet implemented")
    }

    override fun showError(title: String, message: String) {
        this.showErrorAlert(title, message)

    }

}