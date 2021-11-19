package com.example.sanggar.view.activity.jam_operasional

import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sanggar.R
import com.example.sanggar.common.Utilities
import com.example.sanggar.common.clickWithDebounce
import com.example.sanggar.data.model.jam_operasional.JamOperasionalItem
import com.example.sanggar.data.model.jam_operasional.JamOperasionalListResponse
import com.example.sanggar.data.model.jam_operasional.JamOperasionalResponse
import com.example.sanggar.presenter.jam_operasional.JamOperasionalContract
import com.example.sanggar.presenter.jam_operasional.JamOperasionalPresenter
import com.example.sanggar.view.activity.common.BaseActivity
import com.example.sanggar.view.adapter.jam_operasional.JamOperasionalAdapter
import com.example.sanggar.view.fragment.jam_operasional.JamOperasionalBottomSheetFragment
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

        cv_tambah_jam_operasional?.clickWithDebounce {
            val bottomSheet = JamOperasionalBottomSheetFragment()
            bottomSheet.show(supportFragmentManager, "")
        }
    }
    private fun initAdapter() {
        adapter = JamOperasionalAdapter {
            //open dialog
            itemEdit -> val bottomSheet = JamOperasionalBottomSheetFragment(itemEdit)
            bottomSheet.show(supportFragmentManager, "")
        }
//        rv_jam_operasional?.addItemDecoration(DividerItemDecoration(this, RecyclerView.VERTICAL))
        rv_jam_operasional?.layoutManager = LinearLayoutManager(this)
        rv_jam_operasional?.adapter = adapter

//        adapter.setData(getDefaultData())
    }

    //temporary data jen karna blum nyambung api
//    private fun getDefaultData(): MutableList<JamOperasionalItem> {
//        val list = mutableListOf<JamOperasionalItem>()
//        list.add(JamOperasionalItem("Senin", "00:00", "23:59", true))
//        list.add(JamOperasionalItem("Selasa", "00:00", "23:59", true))
//        list.add(JamOperasionalItem("Rabu", "00:00", "23:59", true))
//        list.add(JamOperasionalItem("Kamis", "00:00", "23:59", true))
//        list.add(JamOperasionalItem("Jumat", "00:00", "23:59", true))
//        list.add(JamOperasionalItem("Sabtu", "00:00", "23:59", true))
//        list.add(JamOperasionalItem("Minggu", "00:00", "23:59", true))
//        return list
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

    override fun jamOperasionalResponse(response: JamOperasionalResponse) {
        TODO("Not yet implemented")

    }

    override fun getJamOperasionalResponse(response: JamOperasionalListResponse) {
        isLoading(false)
        response.data?.let { adapter.setData(it) }
    }

    override fun showError(title: String, message: String) {
        showErrorAlert(title, message)
    }
}