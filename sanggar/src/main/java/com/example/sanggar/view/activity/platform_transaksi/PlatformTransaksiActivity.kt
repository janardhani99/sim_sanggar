package com.example.sanggar.view.activity.platform_transaksi


import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sanggar.R
import com.example.sanggar.common.Utilities
import com.example.sanggar.common.clickWithDebounce
import com.example.sanggar.data.model.common.EmptyResponse
import com.example.sanggar.data.model.platform_transaksi.PlatformTransaksiListItem
import com.example.sanggar.data.model.platform_transaksi.PlatformTransaksiListResponse
import com.example.sanggar.data.model.platform_transaksi.PlatformTransaksiResponse
import com.example.sanggar.presenter.platform_transaksi.PlatformTransaksiContract
import com.example.sanggar.presenter.platform_transaksi.PlatformTransaksiPresenter
import com.example.sanggar.view.activity.common.BaseActivity
import com.example.sanggar.view.activity.common.ButtonDialogListener
import com.example.sanggar.view.adapter.platform_transaksi.PlatformTransaksiAdapter
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

        initListener()
        initAdapter()
    }

    private fun initListener() {
        cv_tambah_platform?.clickWithDebounce {
            val intent = Intent(this, DetailPlatformTransaksiActivity::class.java)
            intent.putExtra("intent", 0)
            startActivity(intent)
        }

    }

    private fun initAdapter() {
        adapter = PlatformTransaksiAdapter({detailItem->
            val intent = Intent(this, DetailPlatformTransaksiActivity::class.java)
            intent.putExtra("data", detailItem)
            startActivity(intent)
        },{deleteItem->
            showConfirmationDialog("Konfirmasi","Hapus Platform Ini?", object : ButtonDialogListener {
                override fun onOkButton(dialog: DialogInterface) {
                    isLoading(true)
                    deleteItem.id?.let { presenter.deletePlatformTransaksi(it) }
                    dialog.dismiss()
                    showCustomDialog("hapus data", "Data berhasil dihapus")
                }
            })
        })
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

    override fun platformTransaksiResponse(response: PlatformTransaksiResponse) {
        TODO("Not yet implemented")
    }

    override fun getPlatformTransaksiResponse(response: PlatformTransaksiListResponse) {
        isLoading(false)
        response.data?.let { adapter.setData(it) }
    }

    override fun deletePlatformTransaksiResponse(response: EmptyResponse) {
        isLoading(false)
        fetchData()
    }

    override fun showError(title: String, message: String) {
        this.showErrorAlert(title, message)
    }
}