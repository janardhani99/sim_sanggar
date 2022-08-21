package com.example.sanggar.view.activity.studio

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sanggar.R
import com.example.sanggar.common.Utilities
import com.example.sanggar.common.clickWithDebounce
import com.example.sanggar.data.model.common.EmptyResponse
import com.example.sanggar.data.model.studio.StudioData
import com.example.sanggar.data.model.studio.StudioListResponse
import com.example.sanggar.data.model.studio.StudioResponse
import com.example.sanggar.presenter.studio.StudioContract
import com.example.sanggar.presenter.studio.StudioPresenter
import com.example.sanggar.view.activity.common.BaseActivity
import com.example.sanggar.view.activity.common.ButtonDialogListener
import com.example.sanggar.view.adapter.studio.StudioAdapter
import kotlinx.android.synthetic.main.activity_studio.*

class StudioActivity : BaseActivity(), StudioContract.View {

    var data: StudioData? = null
    private var presenter= StudioPresenter(this)
    lateinit var adapter: StudioAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_studio)
        setToolbar()
        initListener()
        initAdapter()

    }

    fun initListener() {
        cv_tambah_studio?.clickWithDebounce {
            val intent = Intent(this, DetailStudioActivity::class.java)
            intent.putExtra("intent", 0)
            startActivity(intent)
        }
    }

    fun initAdapter() {
        adapter = StudioAdapter({detailItem->
            val intent = Intent(this, DetailStudioActivity::class.java)
            intent.putExtra("data_studio", detailItem)
            startActivity(intent)
        }, {deleteItem->
            showConfirmationDialog("Konfirmasi", "Hapus Studio ini?", object : ButtonDialogListener {
                override fun onOkButton(dialog: DialogInterface) {
                    isLoading(true)
                    deleteItem.id?.let { presenter.deleteStudio(it) }
                    dialog.dismiss()
                    showCustomDialog("Berhasil", "Data Berhasil Dihapus")
                }
            })
        })

        rv_studio?.layoutManager = LinearLayoutManager(this)
        rv_studio?.adapter = adapter

    }

    fun fetchData() {
        isLoading(true)
        presenter.getStudio()
    }

    private fun isLoading(isLoad: Boolean) {
        if(isLoad) Utilities.showProgress(this)
        else Utilities.hideProgress()
    }

    override fun onResume() {
        super.onResume()
        fetchData()
    }

    override fun studioResponse(response: StudioResponse) {
        TODO("Not yet implemented")
    }

    override fun getStudioResponse(response: StudioListResponse) {
        isLoading(false)
        response.data?.let { adapter.setData(it) }
    }

    override fun deleteStudioResponse(response: EmptyResponse) {
        isLoading(false)
        fetchData()
    }

    override fun uploadImageResponse() {
        TODO("Not yet implemented")
    }

    override fun showError(title: String, message: String) {
        this.showErrorAlert(title, message)
    }
}