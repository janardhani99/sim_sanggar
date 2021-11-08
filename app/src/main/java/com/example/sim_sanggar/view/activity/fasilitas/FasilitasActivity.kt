package com.example.sim_sanggar.view.activity.fasilitas

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sim_sanggar.R
import com.example.sim_sanggar.common.Utilities
import com.example.sim_sanggar.common.clickWithDebounce
import com.example.sim_sanggar.data.model.common.EmptyResponse
import com.example.sim_sanggar.data.model.fasilitas.FasilitasListItem
import com.example.sim_sanggar.data.model.fasilitas.FasilitasListResponse
import com.example.sim_sanggar.data.model.fasilitas.FasilitasResponse
import com.example.sim_sanggar.presenter.fasilitas.FasilitasContract
import com.example.sim_sanggar.presenter.fasilitas.FasilitasPresenter
import com.example.sim_sanggar.view.activity.common.BaseActivity
import com.example.sim_sanggar.view.activity.common.ButtonDialogListener
import com.example.sim_sanggar.view.adapter.fasilitas.FasilitasAdapter
import kotlinx.android.synthetic.main.activity_fasilitas.*
import kotlinx.android.synthetic.main.fragment_toolbar.*

class FasilitasActivity : BaseActivity(), FasilitasContract.View {

    var data: FasilitasListItem? = null
    private var presenter = FasilitasPresenter(this)
    lateinit var adapter: FasilitasAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fasilitas)

        data = intent.getParcelableExtra<FasilitasListItem>("data")

        setToolbar()
        toolbar_title?.text = getString(R.string.fasilitas)

//        initListener()
        iniAdapter()
    }

//    When click tambah fasilitas, go to detail page
//    private fun initListener() {
//        cv_tambah_fasilitas?.clickWithDebounce {
//            val intent = Intent(this, DetailFasilitasActivity::class.java)
//            intent.putExtra("intent", 0)
//            startActivity(intent)
//        }
//    }

//    enable action edit and delete
    private fun iniAdapter() {
        adapter = FasilitasAdapter()

//    List of fasilitas data from adapter
        rv_fasilitas?.layoutManager = LinearLayoutManager(this)
        rv_fasilitas?.adapter = adapter

    }

    fun fetchData() {
        isLoading(true)
        presenter.getFasilitas()
    }

    private fun isLoading(isLoad: Boolean) {
        if(isLoad) Utilities.showProgress(this)
        else Utilities.hideProgress()
    }

    override fun onResume() {
        super.onResume()
        fetchData()
    }

    override fun fasilitasResponse(response: FasilitasResponse) {

    }

    override fun getFasilitasResponse(response: FasilitasListResponse) {
        isLoading(false)
        response.data?.let { adapter.setData(it) }
    }

    override fun deleteFasilitasResponse(response: EmptyResponse) {
        isLoading(false)
        fetchData()
    }

    override fun uploadImageResponse() {

    }

    override fun showError(title: String, message: String) {
        this.showErrorAlert(title, message)
    }
}