package com.example.sim_sanggar.view.activity.pembelajaran

import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sim_sanggar.R
import com.example.sim_sanggar.common.Utilities
import com.example.sim_sanggar.common.clickWithDebounce
import com.example.sim_sanggar.data.model.common.EmptyResponse
import com.example.sim_sanggar.data.model.pembelajaran.PembelajaranData
import com.example.sim_sanggar.data.model.pembelajaran.PembelajaranListResponse
import com.example.sim_sanggar.data.model.pembelajaran.PembelajaranResponse
import com.example.sim_sanggar.presenter.pembelajaran.PembelajaranContract
import com.example.sim_sanggar.presenter.pembelajaran.PembelajaranPresenter
import com.example.sim_sanggar.view.activity.common.BaseActivity
import com.example.sim_sanggar.view.activity.common.ButtonDialogListener
import com.example.sim_sanggar.view.adapter.pembelajaran.PembelajaranAdapter
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
        toolbar_title.setText("Materi Belajar")

//        data = intent.getParcelableExtra<PembelajaranData>("data")
        initAdapter()
    }

    private fun initAdapter() {
        adapter = PembelajaranAdapter{
            itemVideo -> val intent = Intent(Intent.ACTION_VIEW, Uri.parse(itemVideo.link_video))
            startActivity(intent)
        }

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

    override fun getPembelajaranResponse(response: PembelajaranListResponse) {
        isLoading(false)
        response.data?.let { adapter.setData(it) }
    }


    override fun showError(title: String, message: String) {
        showErrorAlert(title, message)
    }
}