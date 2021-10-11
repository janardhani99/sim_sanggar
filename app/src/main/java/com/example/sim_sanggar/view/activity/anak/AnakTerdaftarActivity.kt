package com.example.sim_sanggar.view.activity.anak

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sim_sanggar.R
import com.example.sim_sanggar.common.Utilities
import com.example.sim_sanggar.data.model.anak.AnakListItem
import com.example.sim_sanggar.data.model.anak.AnakListResponse
import com.example.sim_sanggar.data.model.anak.AnakResponse
import com.example.sim_sanggar.presenter.anak.AnakContract
import com.example.sim_sanggar.presenter.anak.AnakPresenter
import com.example.sim_sanggar.view.activity.common.BaseActivity
import com.example.sim_sanggar.view.adapter.anakterdaftar.AnakTerdaftarAdapter
import kotlinx.android.synthetic.main.activity_anak_terdaftar.*
import kotlinx.android.synthetic.main.fragment_toolbar.*

class AnakTerdaftarActivity :BaseActivity(), AnakContract.View {

    var data : AnakListItem? = null
    private var presenter = AnakPresenter(this)
    lateinit var adapter: AnakTerdaftarAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_anak_terdaftar)

        data = intent.getParcelableExtra<AnakListItem>("data")

        setToolbar()
        toolbar_title?.text = getString(R.string.riwayat_daftar_anak)

        initAdapter()
    }

    private fun initAdapter() {

        adapter = AnakTerdaftarAdapter { detailItem->
            showCustomDialog("Klik", "Data di klik")
        }
        rv_anak_terdaftar?.layoutManager = LinearLayoutManager(this)
        rv_anak_terdaftar?.adapter = adapter
    }

    private fun fetchData() {
        isLoading(true)
        presenter.getAnak()
    }

    private fun isLoading(isLoad: Boolean) {
        if (isLoad) Utilities.showProgress(this)
        else Utilities.hideProgress()
    }

    override fun onResume() {
        super.onResume()
        fetchData()
    }

    override fun anakResponse(response: AnakResponse) {
        TODO("Not yet implemented")
    }

    override fun getAnakResponse(response: AnakListResponse) {
        isLoading(false)
        response.data?.let { adapter.setData(it) }
    }

    override fun showError(title: String, message: String) {
        this.showErrorAlert(title, message)

    }
}