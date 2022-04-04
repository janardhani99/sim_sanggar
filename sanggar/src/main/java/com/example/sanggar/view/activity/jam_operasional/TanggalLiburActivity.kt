package com.example.sanggar.view.activity.jam_operasional

import android.content.DialogInterface
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sanggar.R
import com.example.sanggar.common.Utilities
import com.example.sanggar.common.clickWithDebounce
import com.example.sanggar.data.model.common.EmptyResponse
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
import com.example.sanggar.view.activity.common.ButtonDialogListener
import com.example.sanggar.view.adapter.jam_operasional.JamOperasionalAdapter
import com.example.sanggar.view.adapter.jam_operasional.TanggalLiburAdapter
import com.example.sanggar.view.fragment.jam_operasional.JamOperasionalBottomSheetFragment
import com.example.sanggar.view.fragment.jam_operasional.TanggalLiburBottomSheetFragment
import kotlinx.android.synthetic.main.activity_jam_operasional.*
import kotlinx.android.synthetic.main.activity_tanggal_libur.*
import kotlinx.android.synthetic.main.fragment_tanggal_libur_bottom_sheet.*
import kotlinx.android.synthetic.main.fragment_toolbar.*
import kotlinx.android.synthetic.main.recycler_tanggal_libur.*
import java.util.*

class TanggalLiburActivity : BaseActivity(), TanggalLiburContract.View {
    private lateinit var adapterTanggalLibur : TanggalLiburAdapter

    private var presenterTanggalLibur = TanggalLiburPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jam_operasional)

        //set toolbar
        setToolbar()
//        toolbar_title?.text = getString(R.string.jam_operasional)
        initAdapter()
        initListener()
        fetchData()
    }



    private fun initListener() {

        cv_tambah_tanggal_libur?.clickWithDebounce {
            val bottomSheet = TanggalLiburBottomSheetFragment()
            bottomSheet.show(supportFragmentManager, "")
        }


    }
    private fun initAdapter() {

        adapterTanggalLibur = TanggalLiburAdapter ({
            itemEdit-> val bottomSheet = TanggalLiburBottomSheetFragment(itemEdit)
            bottomSheet.show(supportFragmentManager, "")
        } , {deleteItem->
            showConfirmationDialog("Konfirmasi", "Apakah anda yakin?", object : ButtonDialogListener {
                override fun onOkButton(dialog: DialogInterface) {
                    isLoading(true)
                    deleteItem.id?.let { presenterTanggalLibur.deleteTanggalLibur(it) }
                    dialog.dismiss()
                }
            })
        })

        rv_tanggal_libur?.layoutManager = LinearLayoutManager(this)
        rv_tanggal_libur?.adapter = adapterTanggalLibur
//        adapter.setData(getDefaultData())
    }

    fun fetchData() {
        isLoading(true)
        presenterTanggalLibur.getTanggalLibur()
    }

    private fun isLoading(isload: Boolean){
        if (isload) Utilities.showProgress(this)
        else {
            Utilities.hideProgress()

        }
    }


    override fun tanggalLiburResponse(response: TanggalLiburResponse) {
        TODO("Not yet implemented")
    }

    override fun getTanggalLiburResponse(response: TanggalLiburListResponse) {
        isLoading(false)
        response.data?.let { adapterTanggalLibur.setData(it) }
    }

    override fun deleteTanggalLiburResponse(response: EmptyResponse) {
        isLoading(false)
        fetchData()
    }

    override fun showError(title: String, message: String) {
        showErrorAlert(title, message)
    }
}