package com.example.sim_sanggar.view.activity.platform_transaksi


import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sim_sanggar.presenter.platform_transaksi.PlatformTransaksiPresenter
import com.example.sim_sanggar.R
import com.example.sim_sanggar.common.Utilities
import com.example.sim_sanggar.common.clickWithDebounce
import com.example.sim_sanggar.data.model.platform_transaksi.PlatformTransaksiListItem
import com.example.sim_sanggar.data.model.platform_transaksi.PlatformTransaksiListResponse
import com.example.sim_sanggar.presenter.platform_transaksi.PlatformTransaksiContract
import com.example.sim_sanggar.view.activity.common.BaseActivity
import com.example.sim_sanggar.view.adapter.platform_transaksi.PlatformTransaksiAdapter
import kotlinx.android.synthetic.main.activity_platform_transaksi.*
import kotlinx.android.synthetic.main.fragment_toolbar.*
import kotlinx.android.synthetic.main.recycler_platform_transaksi.*
import kotlinx.android.synthetic.main.recycler_platform_transaksi.view.*

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

        initAdapter()
        initListener()
    }

    private fun initListener() {
        btn_copy_nomor?.clickWithDebounce {
            copyToClipboard()
        }
    }

    private fun initAdapter() {
        adapter = PlatformTransaksiAdapter()
        rv_platform_transaksi?.layoutManager = LinearLayoutManager(this)
        rv_platform_transaksi?.adapter = adapter
    }

    fun fetchData() {
        isLoading(true)
        presenter.getPlatformTransaksi()
    }

    private fun copyToClipboard() {
        val textToCopy = tv_no_rekening_recycler.text

        val clipboardManager = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clipData = ClipData.newPlainText("text", textToCopy)
        clipboardManager.setPrimaryClip(clipData)

//        Toast.makeText(this, "Nomor berhasil disalin!", Toast.LENGTH_LONG).show()
        this.showCustomDialog("Berhasil", "Nomor berhasil disalin!")
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

    override fun getPlatformTransaksiResponse(response: PlatformTransaksiListResponse) {
        isLoading(false)
        response.data?.let { adapter.setData(it) }
    }

    override fun showError(title: String, message: String) {
        this.showErrorAlert(title, message)
    }
}