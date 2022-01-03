package com.example.sim_sanggar.view.activity.jam_operasional

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sim_sanggar.R
import com.example.sim_sanggar.common.Utilities
import com.example.sim_sanggar.data.model.jam_operasional.JamOperasionalResponse
import com.example.sim_sanggar.presenter.jam_operasional.JamOperasionalContract
import com.example.sim_sanggar.presenter.jam_operasional.JamOperasionalPresenter
import com.example.sim_sanggar.view.activity.common.BaseActivity
import com.example.sim_sanggar.view.adapter.jam_operasional.JamOperasionalAdapter
import kotlinx.android.synthetic.main.activity_jam_operasional.*
import kotlinx.android.synthetic.main.fragment_toolbar.*

class JamOperasionalActivity : BaseActivity(), JamOperasionalContract.View {

    private lateinit var adapter: JamOperasionalAdapter
    private var presenter = JamOperasionalPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jam_operasional)

        //set toolbar
        setToolbar()
        toolbar_title?.text = getString(R.string.jam_operasional)

        initAdapter()
        initListener()
        fetchData()
    }

    private fun initListener() {

        sr_jam_operasional?.setOnRefreshListener {
            fetchData()
        }

    }
    private fun initAdapter() {

        adapter = JamOperasionalAdapter()
//        rv_jam_operasional?.addItemDecoration(DividerItemDecoration(this, RecyclerView.VERTICAL))
        rv_jam_operasional?.layoutManager = LinearLayoutManager(this)
        rv_jam_operasional?.adapter = adapter

//        adapter.setData(getDefaultData())
    }


//    private fun setAdapter() {
//        adapter = JamOperasionalAdapter()
//        rv_jam_operasional?.addItemDecoration(DividerItemDecoration(this, RecyclerView.VERTICAL))
//        rv_jam_operasional?.adapter = adapter
//        adapter.setData(getDefaultData())
//    }

    fun fetchData() {
        isLoading(true)
        presenter.getJamOperasional()
    }

    private fun isLoading(isload: Boolean){
        if (isload) Utilities.showProgress(this)
        else {
            Utilities.hideProgress()
            sr_jam_operasional?.isRefreshing = false
        }
    }
    override fun getJamOperasionalResponse(response: JamOperasionalResponse) {
        isLoading(false)
        response.data?.let { adapter.setData(it) }
    }

    override fun showError(title: String, message: String) {
        showErrorAlert(title, message)
    }
}