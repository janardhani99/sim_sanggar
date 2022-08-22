package com.example.sim_sanggar.view.activity.sewa

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sim_sanggar.R
import com.example.sim_sanggar.common.Utilities
import com.example.sim_sanggar.common.clickWithDebounce
import com.example.sim_sanggar.data.model.sewa.SewaListItem
import com.example.sim_sanggar.data.model.sewa.SewaListResponse
import com.example.sim_sanggar.data.model.sewa.SewaResponse
import com.example.sim_sanggar.presenter.sewa.SewaContract
import com.example.sim_sanggar.presenter.sewa.SewaPresenter
import com.example.sim_sanggar.view.activity.common.BaseActivity
import com.example.sim_sanggar.view.adapter.sewa.PlatformTransaksiActivity
import com.example.sim_sanggar.view.adapter.sewabelumbayaradapter.SewaBelumBayarAdapter
import kotlinx.android.synthetic.main.activity_sewa.*
import kotlinx.android.synthetic.main.activity_sewa.rv_riwayat_sewa


class SewaActivity : BaseActivity(), SewaContract.View {

    lateinit var adapter : SewaBelumBayarAdapter
    var data : SewaListItem? = null
    private var presenter = SewaPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_sewa)

        setToolbar()

        initListener()
        initAdapter()
    }

    private fun initListener() {
        cv_tambah_data_sewa?.clickWithDebounce {
            startActivity(Intent(this, TambahSewaActivity::class.java))
        }

        cv_riwayat_sewa?.clickWithDebounce {
            startActivity(Intent(this, RiwayatSewaActivity::class.java))
        }

        sr_sewa?.setOnRefreshListener {
            fetchData()
        }
    }

    private fun initAdapter() {

        adapter = SewaBelumBayarAdapter {
            detailItem -> val intent = Intent(this, PlatformTransaksiActivity::class.java)
            intent.putExtra("data_sewa", detailItem)
            startActivity(intent)
        }
        rv_riwayat_sewa?.layoutManager = LinearLayoutManager(this)
        rv_riwayat_sewa?.adapter = adapter
    }

    private fun fetchData() {
        isLoading(true)
        presenter.getSewaStatus1("1")
    }

    private fun isLoading(isLoad: Boolean) {
        if (isLoad) Utilities.showProgress(this)
        else {
            Utilities.hideProgress()
            sr_sewa.isRefreshing = false
        }
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
        if (adapter.itemCount !== 0) {
            tv_belum_ada.text = ""
        } else {
            tv_belum_ada.text = "Tidak ada Sewa yang harus di bayar"
        }

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