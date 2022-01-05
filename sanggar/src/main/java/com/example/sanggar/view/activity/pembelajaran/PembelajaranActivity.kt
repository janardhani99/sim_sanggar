package com.example.sanggar.view.activity.pembelajaran

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sanggar.R
import com.example.sanggar.common.Utilities
import com.example.sanggar.common.clickWithDebounce
import com.example.sanggar.data.model.common.EmptyResponse
import com.example.sanggar.data.model.pembelajaran.PembelajaranData
import com.example.sanggar.data.model.pembelajaran.PembelajaranListResponse
import com.example.sanggar.data.model.pembelajaran.PembelajaranResponse
import com.example.sanggar.presenter.pembelajaran.PembelajaranContract
import com.example.sanggar.presenter.pembelajaran.PembelajaranPresenter
import com.example.sanggar.view.activity.common.BaseActivity
import com.example.sanggar.view.activity.common.ButtonDialogListener
import com.example.sanggar.view.adapter.pembelajaran.PembelajaranAdapter
import kotlinx.android.synthetic.main.activity_pembelajaran.*
import kotlinx.android.synthetic.main.activity_platform_transaksi.*
import kotlinx.android.synthetic.main.fragment_toolbar.*

class PembelajaranActivity : BaseActivity(), PembelajaranContract.View {

    var data: PembelajaranData? = null
    private var presenter = PembelajaranPresenter(this)
    lateinit var adapter: PembelajaranAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pembelajaran)

        setToolbar()
        toolbar_title.setText("Pembelajaran")

//        data = intent.getParcelableExtra<PembelajaranData>("data")
        initListener()
        initAdapter()
    }

    private fun initListener() {
        cv_tambah_pembelajaran?.clickWithDebounce {
            val intent = Intent(this, DetailPembelajaranActivity::class.java)
            intent.putExtra("intent", 0)
            startActivity(intent)
        }
    }

    private fun initAdapter() {
        adapter = PembelajaranAdapter({detailItem ->
            val intent = Intent(this, DetailPembelajaranActivity::class.java)
            intent.putExtra("data", detailItem)
            startActivity(intent)
        }, {deleteItem ->
            showConfirmationDialog("Hapus Data", "Hapus Pembelajaran ini?", object : ButtonDialogListener{
                override fun onOkButton(dialog: DialogInterface) {
                    isLoading(true)
                    deleteItem.id?.let { presenter.deletePembelajaran(it) }
                    dialog.dismiss()
                    showCustomDialogBack("Berhasil", "Data berhasil dihapus")
                }
            })
        })

        rv_pembelajaran?.layoutManager = LinearLayoutManager(this)
        rv_pembelajaran?.adapter = adapter
    }

    private fun isLoading(isLoad: Boolean) {
        if (isLoad) Utilities.showProgress(this)
        else Utilities.hideProgress()
    }

    private fun fetchData() {
        isLoading(true)
        presenter.getPembelajaran()
    }

    override fun onResume() {
        super.onResume()
        fetchData()
    }
    override fun pembelajaranResponse(response: PembelajaranResponse) {
        TODO("Not yet implemented")
    }

    override fun getPembelajaranResponse(response: PembelajaranListResponse) {
        isLoading(false)
        response.data?.let { adapter.setData(it) }
    }

    override fun deletePembelajaranResponse(response: EmptyResponse) {
        isLoading(false)
        fetchData()
    }

    override fun showError(title: String, message: String) {
        showErrorAlert(title, message)
    }
}