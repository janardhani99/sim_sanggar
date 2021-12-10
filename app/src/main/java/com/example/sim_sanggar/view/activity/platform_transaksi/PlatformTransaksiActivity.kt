package com.example.sim_sanggar.view.activity.platform_transaksi


import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sim_sanggar.presenter.platform_transaksi.PlatformTransaksiPresenter
import com.example.sim_sanggar.R
import com.example.sim_sanggar.common.Utilities
import com.example.sim_sanggar.data.model.platform_transaksi.PlatformTransaksiListItem
import com.example.sim_sanggar.data.model.platform_transaksi.PlatformTransaksiListResponse
import com.example.sim_sanggar.presenter.platform_transaksi.PlatformTransaksiContract
import com.example.sim_sanggar.view.activity.common.BaseActivity
import com.example.sim_sanggar.view.adapter.platform_transaksi.PlatformTransaksiAdapter
import kotlinx.android.synthetic.main.activity_platform_transaksi.*
import kotlinx.android.synthetic.main.fragment_toolbar.*

class PlatformTransaksiActivity : BaseActivity(), PlatformTransaksiContract.View {

    var data: PlatformTransaksiListItem? = null
    private var presenter = PlatformTransaksiPresenter(this)
    lateinit var adapter: PlatformTransaksiAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_platform_transaksi)

        data = intent.getParcelableExtra<PlatformTransaksiListItem>("data")

        setToolbar()
        toolbar_title?.text = getString(R.string.platform_transaksi)

        initAdapter()
    }


    private fun initAdapter() {
        adapter = PlatformTransaksiAdapter()
        rv_platform_transaksi?.layoutManager = LinearLayoutManager(this)
        rv_platform_transaksi?.adapter = adapter
    }

    fun fetchData() {
        isLoading(true)
        presenter.getPlatformTransaksi()
    }

    private fun isLoading(isLoad: Boolean) {
        if (isLoad) Utilities.showProgress(this)
        else {
            Utilities.hideProgress()
        }
    }

    override fun onResume() {
        super.onResume()
        fetchData()
    }

    override fun getPlatformTransaksiResponse(response: PlatformTransaksiListResponse) {
        isLoading(false)
        response.data?.let { adapter.setData(it) }
    }

    override fun showError(title: String, message: String) {
        this.showErrorAlert(title, message)
    }
}