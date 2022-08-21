package com.example.sanggar.view.activity.sewa

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sanggar.R
import com.example.sanggar.common.Utilities
import com.example.sanggar.data.model.common.EmptyResponse
import com.example.sanggar.data.model.sewa.SewaListItem
import com.example.sanggar.data.model.sewa.SewaListResponse
import com.example.sanggar.data.model.sewa.SewaResponse
import com.example.sanggar.presenter.sewa.SewaListContract
import com.example.sanggar.presenter.sewa.SewaListPresenter
import com.example.sanggar.view.activity.common.BaseActivity
import com.example.sanggar.view.adapter.sewapertanggaladapter.SewaPerTanggalAdapter
import kotlinx.android.synthetic.main.activity_penyewaan.*

class PenyewaanActivity : BaseActivity(), SewaListContract.View {

    var data_sewa: SewaListItem? = null
    var presenter = SewaListPresenter(this)
    lateinit var adapter: SewaPerTanggalAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_penyewaan)
        setToolbar()
        initAdapterTersewa()
        initListener()
    }

    fun initListener() {
        calendar.setOnDateChangeListener { view, year, month, dayOfMonth ->
            val selectedTanggal = "${year}/${month+1}/${dayOfMonth}"
            Log.i("tanggal", selectedTanggal)
            presenter.getTanggalTersewa(selectedTanggal)
        }
    }
    fun initAdapterTersewa() {
        adapter = SewaPerTanggalAdapter()
        rv_booked_tanggal?.layoutManager = LinearLayoutManager(this)
        rv_booked_tanggal?.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        initAdapterTersewa()
    }

    private fun isLoading(isLoad: Boolean) {
        if (isLoad) Utilities.showProgress(this)
        else Utilities.hideProgress()
    }

    override fun sewaListResponse(response: SewaResponse) {
        TODO("Not yet implemented")
    }

    override fun getSewaListResponse(response: SewaListResponse) {
        isLoading(false)
        response.data?.let { adapter.setData(it)}
        if (adapter.itemCount !== 0) {
            tv_belum_ada.text = "Jam Tersewa"
        } else {
            tv_belum_ada.text = "Belum Ada Sewa"
        }
    }

    override fun deleteSewaListResponse(response: EmptyResponse) {
        TODO("Not yet implemented")
    }

    override fun showError(title: String, message: String) {
        showErrorAlert(title, message)
        isLoading(false)
    }
}