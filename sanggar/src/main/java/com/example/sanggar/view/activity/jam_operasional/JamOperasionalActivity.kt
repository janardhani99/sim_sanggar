package com.example.sanggar.view.activity.jam_operasional

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sanggar.R
import com.example.sanggar.common.Utilities
import com.example.sanggar.common.clickWithDebounce
import com.example.sanggar.data.model.jam_operasional.JamOperasionalListResponse
import com.example.sanggar.data.model.jam_operasional.JamOperasionalResponse
import com.example.sanggar.data.model.jam_operasional.TanggalLiburListResponse
import com.example.sanggar.data.model.jam_operasional.TanggalLiburResponse
import com.example.sanggar.presenter.DatePickerHelper
import com.example.sanggar.presenter.jam_operasional.JamOperasionalContract
import com.example.sanggar.presenter.jam_operasional.JamOperasionalPresenter
import com.example.sanggar.presenter.jam_operasional.TanggalLiburContract
import com.example.sanggar.presenter.jam_operasional.TanggalLiburPresenter
import com.example.sanggar.view.activity.common.BaseActivity
import com.example.sanggar.view.adapter.jam_operasional.JamOperasionalAdapter
import com.example.sanggar.view.adapter.jam_operasional.TanggalLiburAdapter
import com.example.sanggar.view.fragment.jam_operasional.JamOperasionalBottomSheetFragment
import com.example.sanggar.view.fragment.jam_operasional.TanggalLiburBottomSheetFragment
import kotlinx.android.synthetic.main.activity_jam_operasional.*
import kotlinx.android.synthetic.main.fragment_tanggal_libur_bottom_sheet.*
import kotlinx.android.synthetic.main.fragment_toolbar.*
import kotlinx.android.synthetic.main.recycler_tanggal_libur.*
import java.util.*

class JamOperasionalActivity : BaseActivity(), JamOperasionalContract.View, TanggalLiburContract.View {
    private lateinit var adapter: JamOperasionalAdapter
    private lateinit var adapterTanggalLibur : TanggalLiburAdapter

    private var presenter = JamOperasionalPresenter(this)
    private var presenterTanggalLibur = TanggalLiburPresenter(this)

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

        cv_tambah_jam_operasional?.clickWithDebounce {
            val bottomSheet = JamOperasionalBottomSheetFragment()
            bottomSheet.show(supportFragmentManager, "")
        }

        cv_tambah_tanggal_libur?.clickWithDebounce {
            val bottomSheet = TanggalLiburBottomSheetFragment()
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

        adapterTanggalLibur = TanggalLiburAdapter {
            itemEdit-> val bottomSheet = TanggalLiburBottomSheetFragment(itemEdit)
            bottomSheet.show(supportFragmentManager, "")
        }

        rv_tanggal_libur?.layoutManager = LinearLayoutManager(this)
        rv_tanggal_libur?.adapter = adapterTanggalLibur
//        adapter.setData(getDefaultData())
    }

    //temporary data
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
        presenterTanggalLibur.getTanggalLibur()
    }

    private fun isLoading(isload: Boolean){
        if (isload) Utilities.showProgress(this)
        else {
            Utilities.hideProgress()

        }
    }

    override fun jamOperasionalResponse(response: JamOperasionalResponse) {
        TODO("Not yet implemented")

    }

    override fun getJamOperasionalResponse(response: JamOperasionalListResponse) {
        isLoading(false)
        response.data?.let { adapter.setData(it) }
    }

    override fun tanggalLiburResponse(response: TanggalLiburResponse) {
        TODO("Not yet implemented")
    }

    override fun getTanggalLiburResponse(response: TanggalLiburListResponse) {
        isLoading(false)
        response.data?.let { adapterTanggalLibur.setData(it) }
    }

    override fun showError(title: String, message: String) {
        showErrorAlert(title, message)
    }
}