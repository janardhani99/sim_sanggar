package com.example.sanggar.view.activity.kegiatan

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sanggar.R
import com.example.sanggar.common.Constants
import com.example.sanggar.common.Utilities
import com.example.sanggar.common.clickWithDebounce
import com.example.sanggar.data.model.common.EmptyResponse
import com.example.sanggar.data.model.kegiatan.KegiatanListItem
import com.example.sanggar.data.model.kegiatan.KegiatanListResponse
import com.example.sanggar.data.model.kegiatan.KegiatanResponse
import com.example.sanggar.presenter.kegiatan.KegiatanContract
import com.example.sanggar.presenter.kegiatan.KegiatanPresenter
import com.example.sanggar.view.activity.common.BaseActivity
import com.example.sanggar.view.activity.common.ButtonDialogListener
import com.example.sanggar.view.adapter.kegiatan.KegiatanAdapter
import kotlinx.android.parcel.Parcelize
import kotlinx.android.synthetic.main.activity_kegiatan.*
import kotlinx.android.synthetic.main.fragment_toolbar.*

class KegiatanActivity : BaseActivity(), KegiatanContract.View {
    var data: KegiatanListItem? = null
    private var presenter = KegiatanPresenter(this)
    lateinit var adapter: KegiatanAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kegiatan)
        data = intent.getParcelableExtra<KegiatanListItem>("data")
        setToolbar()
        toolbar_title?.text = getString(R.string.kegiatan)

        initListener()
        initAdapter()
        fetchData()
    }

    private fun initListener() {
        cv_tambah_kegiatan?.clickWithDebounce {
            val intent = Intent(this, DetailKegiatanActivity::class.java)
            intent.putExtra("intent", 0)
            startActivity(intent)
        }

    }

    private fun initAdapter() {
        adapter = KegiatanAdapter({detailItem->
//            startActivity(Intent(this, DetailKegiatanActivity::class.java).getParcelableExtra("data"))
        },{deleteItem->
            showConfirmationDialog("Konfirmasi","Hapus Kegiatan?", object : ButtonDialogListener{
                override fun onOkButton(dialog: DialogInterface) {
                    isLoading(true)
                    deleteItem.id?.let { presenter.deleteKegiatan(it) }
                    dialog.dismiss()
                    showCustomDialog("hapus data", "Data berhasil dihapus")
                }
            })


        })
        rv_kegiatan?.layoutManager = LinearLayoutManager(this)
        rv_kegiatan?.adapter = adapter
    }

    fun fetchData() {
        isLoading(true)
        presenter.getKegiatan()
    }

    private fun isLoading(isLoad: Boolean) {
        if (isLoad) Utilities.showProgress(this)
        else {
            Utilities.hideProgress()
        }
    }

    override fun kegiatanResponse(response: KegiatanResponse) {
        TODO("Not yet implemented")
    }

    override fun getKegiatanResponse(response: KegiatanListResponse) {
        isLoading(false)
        response.data?.let { adapter.setData(it) }
    }

    override fun deleteKegiatanResponse(response: EmptyResponse) {
        isLoading(false)
        fetchData()

    }

    override fun showError(title: String, message: String) {
        TODO("Not yet implemented")
    }

}